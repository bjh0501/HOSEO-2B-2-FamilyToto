package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CaptchaService {
	public int isRight(HttpSession session, HttpServletRequest request);
}