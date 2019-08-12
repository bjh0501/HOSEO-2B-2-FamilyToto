package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface CaptchaService {
	int isRight(HttpSession session, HttpServletRequest request);
	void create(HttpServletResponse response, HttpSession session);
	void voice(HttpServletRequest request, HttpServletResponse response);
}