package com.familytoto.familytotoProject.toto.dao;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

public interface LadderDao {
	// 사다리 배팅시작
	int insertLadderBet(LadderVO vo);
	
	// 사다리 크레딧 소모
	int insertLadderCredit(CreditVO vo);
}
