package com.familytoto.familytotoProject.toto.service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

public interface LadderService {
	// 사다리 배팅시작
	int insertLadderBet(LadderVO vo);
	
	// 사다리 크레딧 소모
	int insertLadderCredit(CreditVO vo);
}
