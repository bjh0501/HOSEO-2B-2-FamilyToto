package com.familytoto.familytotoProject.charge.service;

import java.util.Map;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ChargeService {
	Map<String, Object> getCreditInfo(CustVO vo);
	int doCharge(CreditVO vo);
	
	// 충전 내역 메일
	void sendHistoryEmail(String to, int credit, String gubun, String nickname);
	
	// 현재 보유 크레딧 보기
	int getCurrentCredit(int familyCustNo);
	
	// 카드 정보보기
	CreditVO getCardInfo(int familyCustNo);
}
