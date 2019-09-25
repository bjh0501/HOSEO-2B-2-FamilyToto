package com.familytoto.familytotoProject.toto.service;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

public interface LadderService {
	// 사다리 배팅시작
	int insertLadderBet(LadderVO vo,
			CreditVO creVo,
			CustVO cVo,
			HttpServletRequest request);
	
	void initFirstGubun();
}
