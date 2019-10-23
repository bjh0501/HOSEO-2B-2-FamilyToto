package com.familytoto.familytotoProject.login.dao;

import java.util.List;

import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustLoginDAO {
	CustVO login(CustVO vo);
	
	// 패밀리 회원 경험치가져오기
	CustVO getExpInfo(int familyCustNo);
	
	// 찜한거 가져오기
	List<String> listPreferProduct(int familyCustNo);
	
	// VIP만료 업데이트
	int updateVipticketExpire(ExpVO vo);
	
	// VIP만료 경험치 업데이트
	int updateVipTicketExpireExp(int familyCustNo);
}
