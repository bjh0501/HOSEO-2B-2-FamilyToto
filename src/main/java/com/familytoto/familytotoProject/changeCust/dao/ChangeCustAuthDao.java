package com.familytoto.familytotoProject.changeCust.dao;

import java.util.Map;

import com.familytoto.familytotoProject.login.domain.SocialVO;

public interface ChangeCustAuthDao {
	
	// 소셜로그인 
	Map<String, Object> checkSocial(SocialVO vo);
	
	// 소셜 연동
	int authSocial(SocialVO vo);
	
	// 연동해제
	int unAuthSocial(SocialVO vo);
	
}
