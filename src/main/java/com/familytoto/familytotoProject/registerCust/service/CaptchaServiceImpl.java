package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {

	@Override
	public int isRight(HttpSession session, HttpServletRequest request) {
		String sCaptcha = request.getParameter("captcha");
		String answer = (String) session.getAttribute("captcha");
		
		if (sCaptcha.equals(answer)) {
			return 1;
		}
		return 0;
	}
	
}
