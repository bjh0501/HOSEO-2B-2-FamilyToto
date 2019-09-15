package com.familytoto.familytotoProject.toto.service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CommonService {
	// 크레딧가져오기
	public CreditVO getCustCredit(CustVO vo);
}
