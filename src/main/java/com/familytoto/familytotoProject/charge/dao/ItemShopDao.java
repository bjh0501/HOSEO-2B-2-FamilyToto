package com.familytoto.familytotoProject.charge.dao;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ItemShopDao {
	// 크레딧 있는지 여부
	public boolean checkBuyCharge(CreditVO vo);
	
	// 크레딧 소모
	public int insertCredit(CreditVO vo);
	
	// 닉네임체크
	public boolean isCheckNickname(String nickname);
	
	// 닉네임업뎃
	public int updateNickname(CustVO cVo);
}
