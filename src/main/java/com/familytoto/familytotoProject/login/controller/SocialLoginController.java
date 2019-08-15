package com.familytoto.familytotoProject.login.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.login.service.kakao.KakaoLoginApi;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginService;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginVO;

@Controller
public class SocialLoginController {
	@Autowired
	NaverLoginVO naverLoginVO;
	
	@Autowired
	NaverLoginService naverLoginService; 
	
	@Autowired
	KakaoLoginApi kakaoLoginApi;
	
	@RequestMapping("login/social/naver")
	public int callback(HttpServletRequest request, Model model, @RequestParam String state, HttpSession session, HttpServletResponse response)
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
			naverLoginVO.naverLogin(model, code,state, session, request);
			out.println("<script>window.close();opener.document.location.replace('/');</script>");
			
		}
		
		out.flush();		
		return nResult;
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
			} else {
				out.println("<script>"
						+ "alert('다른사람이 쓰고있는 계정이라 연동을 할 수 없습니다.');"
						+ "window.close();</script>");
			}
		}
		
		out.flush();		
		return nResult;
	}
	
	@RequestMapping("login/social/kakao/auth")
	@ResponseBody
	public String kakaoAuth(@RequestParam("code") String code, HttpSession session) {
		String access_Token = kakaoLoginApi.getAccessToken(code);
		
		HashMap<String, Object> userInfo = kakaoLoginApi.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    
	    // 연동해제
//	    HashMap<String, Object> kakaoUnAuth = kakaoLoginApi.kakaoUnAuth(access_Token);
//	    System.out.println("un : " + kakaoUnAuth);
	    
	    
	    //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
	    
		return userInfo.toString();
	}
}
