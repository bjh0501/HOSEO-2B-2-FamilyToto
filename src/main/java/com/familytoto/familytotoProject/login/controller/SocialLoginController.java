package com.familytoto.familytotoProject.login.controller;


import java.io.IOException;
import java.io.PrintWriter;

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

import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.login.service.kakao.KakaoLoginVO;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginVO;
import com.familytoto.familytotoProject.login.service.social.SocalLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class SocialLoginController {
	@Autowired
	NaverLoginVO naverLoginVO;
	
	@Autowired
	SocalLoginService naverLoginService; 
	
	@Autowired
	KakaoLoginVO kakaoLoginVO; 
	
	@Autowired
	SocalLoginService socalLoginService;
	
	@RequestMapping("login/social/naver")
	@ResponseBody
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
			int nNaverResult = naverLoginVO.naverLogin(model, code,state, session, request);

			if(nNaverResult == 0) {
				out.println("<script>window.close();opener.document.location.replace('/');</script>");				
			} else if(nNaverResult == -99) {
				out.println("<script>alert('체크항목에 전부 체크해주세요.');window.close();</script>");				
			} else if(nNaverResult == -98) {
				out.println("<script>alert('성인만 가입할 수 있습니다.');window.close();</script>");				
			}
		}
		
		out.flush();		
		return nResult;
	}
	 
	@RequestMapping("login/social/kakao")
	@ResponseBody
	public int kakaoAuth(@RequestParam("code") String code, HttpSession session, HttpServletResponse response) {
		String access_Token = kakaoLoginVO.getAccessToken(code, "login");
		
		SocialVO vo = kakaoLoginVO.getKakaoLogin(access_Token, session);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(vo != null) {
				if(vo.getScCustId().equals("-99")) {
					out.println("<script>alert('제공 항목을 전부 체크해주세요.'); window.close();</script>");
				} else if(vo.getScCustId().equals("-98")) { 
					out.println("<script>alert('20세 미만은 가입할 수 없습니다.'); window.close();</script>");
				} else {
					out.println("<script>window.close();opener.document.location.replace('/');</script>");
					out.flush();
					return 0;
				}
				
				out.flush();
			}
		} catch(Exception e) { }
	    
		return -1;
	}
	
	@RequestMapping("/login/social/facebook")
	@ResponseBody
	public int facebookAuth(@ModelAttribute SocialVO vo, HttpSession session) {
		// 소셜 부분 
		CustVO cVo = socalLoginService.getSocialFamilyNo(vo);
		
		int nScCustNo = socalLoginService.insertSocialId(vo);
		
		// insert 실패경우
		if(nScCustNo == -99) {
			return -1;
		}
		
		vo.setScCustNo(nScCustNo);
		cVo.setCustNo(nScCustNo);
		
		//페북은 이름 = 닉네임
		cVo.setFamilyCustNick(vo.getScCustNick());
		session.setAttribute("cust", cVo); // 세션 생성
		
		session.setAttribute("custSocial", vo); // 세션 생성
		session.setAttribute("social", "FA"); // 세션 생성
		// 소셜 부분
		
		return 0;
	}		
}
