package com.familytoto.familytotoProject.toto.dao;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CommonDao {
	// 크레딧가져오기
	public CreditVO getCustCredit(CustVO vo);
	
	// 배팅할 때 돈확인
	public boolean isHaveCredit(CreditVO vo); 
}
