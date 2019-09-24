package com.familytoto.familytotoProject.login.service;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustLoginService {
	// 로그인
	CustVO login(CustVO vo);
	
	// 패밀리 회원 경험치가져오기
	CustVO getExpInfo(int familyCustNo);
}
