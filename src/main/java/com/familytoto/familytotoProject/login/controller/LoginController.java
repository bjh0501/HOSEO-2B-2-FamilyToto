package com.familytoto.familytotoProject.login.controller;

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

import com.familytoto.familytotoProject.login.service.CustLoginService;
import com.familytoto.familytotoProject.login.service.facebook.FacebookLoginVO;
import com.familytoto.familytotoProject.login.service.kakao.KakaoLoginVO;
import com.familytoto.familytotoProject.login.service.social.SocalLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class LoginController {
	@Autowired
	CustLoginService custLoginService;
	
	@Autowired
	SocalLoginService naverLoginService;
	
	@Autowired
	KakaoLoginVO kakaoLoginVo;
	
	@Autowired
	FacebookLoginVO facebookLoginVo;
	
	@RequestMapping("login")
    public String login(Model model, HttpSession session, HttpServletRequest request) {
		String sRefer = request.getHeader("referer");
		
		if(sRefer != null && sRefer.indexOf("onesports.ga") >= 1) {
			model.addAttribute("referUrl", sRefer);
			
			if(sRefer.indexOf("findID_PW") >= 1 ||
					sRefer.indexOf("registerCust") >= 1 ||
					sRefer.indexOf("login") >= 1) {
				model.addAttribute("referUrl", "/");
			}
			
		} else {
			model.addAttribute("referUrl", "/");
		}
		
		String sNaverLoginLink = naverLoginService.socialAuthLink(model, session);
		String sKakaoLoginLink = kakaoLoginVo.getKakaoLink();
		String sFacebookLoginLink = facebookLoginVo.getLoginLink();
		
		model.addAttribute("naverLoginUrl", sNaverLoginLink);
		model.addAttribute("kakaoLoginUrl", sKakaoLoginLink);
		model.addAttribute("facebookLoginUrl", sFacebookLoginLink);
		
		return "/loginInfo/login";
    }
	
	@RequestMapping("logout")
	public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sRefer = request.getHeader("referer");
		
		if(sRefer != null && sRefer.indexOf("onesports.ga") >= 1) {
			session.removeAttribute("cust");
			session.removeAttribute("social");
			session.removeAttribute("custSocial");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='" + sRefer + "';</script>");
			out.flush();
		}
	}
	
	// 아래처럼 구현해야하는데 오류
//	@RequestMapping("logout")
//    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		session.removeAttribute("cust");
//		response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<script>document.referrer ? location.replace(document.referrer) : location.href='/';</script>");
//        out.flush();
//    }
	
	
	// 원스포츠 회원전용
	@RequestMapping("login/custLogin")
	@ResponseBody
	public int custLogin(@ModelAttribute CustVO vo, HttpServletRequest request) {
		Map<String, Object> login = custLoginService.login(vo);
		int nReuslt = 0;
		
		if(login != null) { // 로그인성공
			HttpSession session = request.getSession();
			vo.setFamilyCustNo(Integer.parseInt(login.get("familyCustNo").toString()));
			vo.setCustNo(Integer.parseInt(login.get("custNo").toString()));
			vo.setFamilyCustEmail(login.get("familyCustEmail").toString());
			vo.setFamilyCustNick(login.get("familyCustNickname").toString());
			vo.setCustPassword("");
			
			session.setAttribute("cust", vo);
			session.setAttribute("social", "ON"); // 세션 생성
			nReuslt = 0;
		} else {
			nReuslt = -99;
		}
		
		return nReuslt;
	}
}

