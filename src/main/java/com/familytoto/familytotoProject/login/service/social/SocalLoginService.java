package com.familytoto.familytotoProject.login.service.social;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface SocalLoginService {
	String socialAuthLink(Model model, HttpSession session);
	String socialAuthChangeLink(Model model, HttpSession session);
	int insertSocialId(SocialVO vo);
	CustVO getSocialFamilyNo(SocialVO vo);
}
