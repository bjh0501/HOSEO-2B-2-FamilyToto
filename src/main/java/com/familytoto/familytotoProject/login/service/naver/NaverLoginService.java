package com.familytoto.familytotoProject.login.service.naver;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface NaverLoginService {
	String naverAuthLink(Model model, HttpSession session);
	String naverAuthChangeLink(Model model, HttpSession session);
	int insertNaverId(Map<String, Object> map);
}
