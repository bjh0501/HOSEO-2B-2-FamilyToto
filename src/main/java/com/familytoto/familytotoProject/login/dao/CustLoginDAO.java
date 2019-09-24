package com.familytoto.familytotoProject.login.dao;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustLoginDAO {
	CustVO login(CustVO vo);
	
	// 패밀리 회원 경험치가져오기
	CustVO getExpInfo(int familyCustNo);
}
