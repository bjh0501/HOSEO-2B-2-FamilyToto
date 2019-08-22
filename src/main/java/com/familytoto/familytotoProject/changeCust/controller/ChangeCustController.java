package com.familytoto.familytotoProject.changeCust.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.changeCust.service.ChangeCustAuthService;
import com.familytoto.familytotoProject.changeCust.service.ChangeCustService;
import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.login.service.kakao.KakaoLoginVO;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginVO;
import com.familytoto.familytotoProject.login.service.social.SocalLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Controller
public class ChangeCustController {
	@Autowired
	ChangeCustService changeCustService;
	
	@Autowired
	ChangeCustAuthService changeCustAuthService; 
	
	@Autowired
	SocalLoginService naverLoginService;
	
	@Autowired
	NaverLoginVO naverLoginVO;
	
	@Autowired
	KakaoLoginVO kakaoLoginVO;
	
	private String sCurrentPassword = ""; 
	
	@RequestMapping("changeCust")
    public ModelAndView changeCust(ModelAndView mv, HttpSession session, HttpServletResponse response, Model model) {
		
		// 소셜 아이디
		CustVO vo = (CustVO) session.getAttribute("cust");
		
		if(vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 개인정보를 수정할 수 없습니다.'); history.go(-1);</script>");
	            out.flush();
			} catch(Exception e) {}
		
			mv.setViewName("redirect:/");
			
			return mv;
		}
		
		String sNaverLoginLink = naverLoginService.socialAuthChangeLink(model, session);
		String sKakaoLoginLink = kakaoLoginVO.getKakaoAuthLink();
		mv.addObject("naverUrl", sNaverLoginLink);
		mv.addObject("kakaoUrl", sKakaoLoginLink);
		
		Map<String, Object> map = changeCustService.getCustInfo(vo);
		sCurrentPassword = (String) map.get("custPassword");
		mv.setViewName("loginInfo/changeCust");
		mv.addObject("list", map);
		
        return mv;
    }
	
	@RequestMapping("changeCust/change")
	@ResponseBody
    public int change(ModelAndView mv, HttpSession session, 
    		@ModelAttribute CustVO cVo,@ModelAttribute RegisterCustVO rVo,
    		HttpServletRequest request) {
			String sFrontCurrentPass = request.getParameter("currentPassword");
			String sFrontNewPass = request.getParameter("newPassword");
		
		cVo.setCustPassword(sFrontCurrentPass);
		
		if(cVo.isDecodePassword(cVo, sCurrentPassword)) {
			CustVO vo = (CustVO) session.getAttribute("cust");
			
			cVo.setChgCustNo(vo.getCustNo());
			cVo.setChgIp(request.getRemoteAddr());
			cVo.setFamilyCustNo(vo.getFamilyCustNo());
			
			// 임시방편
			// 마이바티스 if문 쓸줄알면 나중에 변경예정
			if(!sFrontNewPass.equals("")) {
				cVo.setCustPassword(cVo.toEncodePassword(sFrontNewPass));
			} else {
				cVo.setCustPassword(cVo.toEncodePassword(sFrontCurrentPass));
			}
			
			rVo.setChgIp(request.getRemoteAddr());
			rVo.setFamilyCustNo(vo.getFamilyCustNo());
			
			return changeCustService.updateCustInfo(cVo, rVo);
		} else {
			return -99;
		}
	}
	
	@RequestMapping("changeCust/dropCust")
	@ResponseBody
    public int change(HttpSession session, @ModelAttribute CustVO vo, HttpServletRequest request) {
		CustVO sessionVo = (CustVO) session.getAttribute("cust");
		
		Map<String, Object> map = changeCustService.getCheckPassword(sessionVo);
		
		if(map != null) {
			String sDbPass = map.get("custPassword").toString();
			
			// 비번옳은경우
			if(vo.isDecodePassword(vo, sDbPass)) {
				vo = (CustVO) session.getAttribute("cust");
				vo.setChgIp(request.getRemoteAddr());
				vo.setChgIp(request.getRemoteAddr());
				//트랜잭션 해야함
				changeCustService.updateDropCust(vo);
				changeCustService.updateDropFamilyCust(vo);
				changeCustService.updateInterAuth(vo);
				// 
				session.removeAttribute("cust");
			} else {
				return -98;
			}
		} else {
			return -99;
		}
		return 0;
	}
	
	@RequestMapping("login/social/naver/auth")
	@ResponseBody
	public int callbackAuth(HttpServletRequest request, Model model, @RequestParam String state, HttpSession session, HttpServletResponse response)
			throws IOException, ParseException {
		int nResult = 0;
		String code = "";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("code") == null) {
			nResult = -99;
			out.println("<script>alert('에러가 발생하였습니다. 다시 시도해주세요.'); window.close()</script>");
		} else {
			code = request.getParameter("code");
			Map<String, Object> map = naverLoginVO.naverAuth(code,state, session, request);
			
			if(map.get("error").toString().equals("0")) {
				out.println("<script>"
						+ "alert('연동에 성공하였습니다.');"
						+ "opener.parent.successNaver('" + map.get("scCustEmail").toString() + "');"
						+ "window.close();"
						+ "</script>");
			} else if(map.get("error").toString().equals("-98")) {
				out.println("<script>"
						+ "alert('선택 사항을 모두 체크해주세요.');"
						+ "window.close();</script>");
			} else {
				out.println("<script>"
						+ "alert('다른사람이 쓰고있는 계정이라 연동을 할 수 없습니다.');"
						+ "window.close();</script>");
			}
		}
		
		out.flush();		
		return nResult;
	}
	
	@RequestMapping("changeCust/social/naver/unAuth")
	@ResponseBody
    public int unAuthNaver(HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("social") != null) {
			if(session.getAttribute("social").equals("NA")) {
				return -99;
			}
		}
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		SocialVO vo = new SocialVO();
		
		vo.setChgIp(request.getRemoteAddr());
		vo.setChgCustNo(cVo.getChgCustNo());
		vo.setScCustGubun("NA");
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		return changeCustAuthService.updateUnAuthSocial(vo);
	}
	
	@RequestMapping("login/social/kakao/auth")
	@ResponseBody
	public int kakaoAuth(@RequestParam("code") String code, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		int nResult = 0;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("code") == null) {
			nResult = -99;
			out.println("<script>alert('에러가 발생하였습니다. 다시 시도해주세요.'); window.close()</script>");
		} else {
			code = request.getParameter("code");
			String access_Token = kakaoLoginVO.getAccessToken(code, "auth");
			Map<String, Object> map = kakaoLoginVO.kakaoAuth(access_Token, session, request);
			
			if(map.get("error").toString().equals("0")) {
				out.println("<script>"
						+ "alert('연동에 성공하였습니다.');"
						+ "opener.parent.successKakao('" + map.get("scCustEmail").toString() + "');"
						+ "window.close();"
						+ "</script>");
			} else {
				out.println("<script>"
						+ "alert('다른사람이 쓰고있는 계정이라 연동을 할 수 없습니다.');"
						+ "window.close();</script>");
			}
		}
		
		out.flush();		
		return nResult;
	}
	
	@RequestMapping("changeCust/social/kakao/unAuth")
	@ResponseBody
    public int unAuthKakao(HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("social") != null) {
			if(session.getAttribute("social").equals("KA")) {
				return -99;
			}
		}
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		SocialVO vo = new SocialVO();
		
		vo.setChgIp(request.getRemoteAddr());
		vo.setChgCustNo(cVo.getChgCustNo());
		vo.setScCustGubun("KA");
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		return changeCustAuthService.updateUnAuthSocial(vo);
	}
	
	@RequestMapping("login/social/facebook/auth")
	@ResponseBody
	public int facebookAuth(@ModelAttribute SocialVO sVo, HttpSession session, HttpServletRequest request) throws IOException {
		int nResult = 0;
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
    	
		sVo.setRegIp(request.getRemoteAddr());
		sVo.setFamilyCustNo(cVo.getFamilyCustNo());
		sVo.setChgCustNo(cVo.getCustNo());
		
		nResult = changeCustAuthService.updateAuthSocial(sVo);
		
		// 연동성공
		if( nResult == 1) {
			return 0;
		} else if( nResult == -99) { // 다른사람이 연동한계정
			return -99;
		} else { // 알수없는에러
			return -98;
		}
	}
	
	@RequestMapping("changeCust/social/facebook/unAuth")
	@ResponseBody
    public int unAuthFacebook(HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("social") != null) {
			if(session.getAttribute("social").equals("FA")) {
				return -99;
			}
		}
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		SocialVO vo = new SocialVO();
		
		vo.setChgIp(request.getRemoteAddr());
		vo.setChgCustNo(cVo.getChgCustNo());
		vo.setScCustGubun("FA");
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		return changeCustAuthService.updateUnAuthSocial(vo);
	}
}
