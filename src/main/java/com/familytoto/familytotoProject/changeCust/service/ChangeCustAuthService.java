package com.familytoto.familytotoProject.changeCust.service;

import com.familytoto.familytotoProject.login.domain.SocialVO;

public interface ChangeCustAuthService {
	
	// 새 연동
	int updateAuthSocial(SocialVO vo);
	
	// 연동해제
	int updateUnAuthSocial(SocialVO vo);
	//
}
