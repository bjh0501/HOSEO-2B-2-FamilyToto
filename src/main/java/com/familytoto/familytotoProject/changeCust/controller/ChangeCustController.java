package com.familytoto.familytotoProject.changeCust.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.changeCust.service.ChangeCustAuthService;
import com.familytoto.familytotoProject.changeCust.service.ChangeCustService;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Controller
public class ChangeCustController {
	@Autowired
	ChangeCustService changeCustService;
	
	@Autowired
	ChangeCustAuthService changeCustAuthService; 
	
	@Autowired
	NaverLoginService naverLoginService;
	
	private String sCurrentPassword = ""; 
	
	@RequestMapping("changeCust")
    public ModelAndView changeCust(ModelAndView mv, HttpSession session, HttpServletResponse response, Model model) {
		if(session.getAttribute("social") != null) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('소셜아이디는 개인정보를 수정할 수 없습니다.'); history.go(-1);</script>");
	            out.flush();
			} catch(Exception e) {}
			
			mv.setViewName("redirect:/");
		} else {
			String sNaverLoginLink = naverLoginService.naverAuthChangeLink(model, session);
			mv.addObject("naverUrl", sNaverLoginLink);
			
			CustVO vo = (CustVO) session.getAttribute("cust");
			
			Map<String, Object> map = changeCustService.getCustInfo(vo);
			sCurrentPassword = (String) map.get("custPassword");
			mv.setViewName("loginInfo/changeCust");
			mv.addObject("list", map);
		}
		
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
    public int change(HttpSession session, @ModelAttribute CustVO vo) {
		CustVO sessionVo = (CustVO) session.getAttribute("cust");
		
		Map<String, Object> map = changeCustService.getCheckPassword(sessionVo);
		
		if(map != null) {
			String sDbPass = map.get("custPassword").toString();
			
			// 비번옳은경우
			if(vo.isDecodePassword(vo, sDbPass)) {
				vo = (CustVO) session.getAttribute("cust");
				//트랜잭션 해야함
				changeCustService.updateDropCust(vo);
				changeCustService.updateDropFamilyCust(vo);
				session.removeAttribute("cust");
			} else {
				return -98;
			}
		} else {
			return -99;
		}
		return 0;
	}
	
	@RequestMapping("changeCust/social/naver/unAuth")
	@ResponseBody
    public int unAuthNaver(HttpSession session, HttpServletRequest request) {
		CustVO vo = (CustVO) session.getAttribute("cust");
		vo.setChgIp(request.getRemoteAddr());
		vo.setChgCustNo(vo.getCustNo());
		
		return changeCustAuthService.updateUnAuthNaver(vo);
	}
}
