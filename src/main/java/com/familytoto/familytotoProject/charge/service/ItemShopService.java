package com.familytoto.familytotoProject.charge.service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ItemShopService {	// 크레딧 있는지 여부
	public boolean checkBuyCharge(CreditVO vo);
	
	// 크레딧 소모
	public int insertCredit(CreditVO vo);
		
	// 닉네임체크
	public boolean isCheckNickname(String nickname);
	
	// 닉네임업뎃
	public int updateNickname(CustVO cVo);
	
	// VIP권 구매
	int insertVipTicket(ExpVO vo);
	
	// VIP권 체크
	boolean isVipTicket(int familyCustNo);
	
	// VIP권 경험치얻기
	int updateVipExp(int familyCustNo);
}
