package com.familytoto.familytotoProject.login.dao;

import java.util.Map;

import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface SocialDao {
	int insertSocial(SocialVO vo);
	Map<String, Object> checkSocial(SocialVO vo);
	CustVO getSocialFamilyNo(SocialVO vo);
	CustVO getsocialLoginNoFamilyNo(SocialVO vo);
}
