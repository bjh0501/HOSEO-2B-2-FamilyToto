package com.familytoto.familytotoProject.login.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.login.service.CustLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class LoginController {
	@Autowired
	CustLoginService custLoginService;
	
	@RequestMapping("login")
    public String login() {
		return "loginInfo/login";
    }
	
	@RequestMapping("logout")
	public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		session.removeAttribute("cust");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='/';</script>");
		out.flush();
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
			session.setAttribute("cust", vo);
			nReuslt = 0;
		} else {
			nReuslt = -99;
		}
		
		return nReuslt;
	}
	
	// 소셜로그인
	@RequestMapping("login/socialLogin")
	@ResponseBody
	public int socialLogin(@ModelAttribute CustVO vo, HttpServletRequest request) {
		Map<String, Object> login = custLoginService.login(vo);
		int nReuslt = 0;
		
		if(login != null) { // 로그인성공
			HttpSession session = request.getSession();
			session.setAttribute("cust", vo);
			nReuslt = 0;
		} else {
			nReuslt = -99;
		}
		
		return nReuslt;
	}
}
