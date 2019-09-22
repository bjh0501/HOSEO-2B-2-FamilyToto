package com.familytoto.familytotoProject.toto.dao;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.DiceVO;

public interface DiceDao {
	//  배팅
	int insertDiceBet(DiceVO vo);
	
	//  크레딧 결과
	int insertCredit(CreditVO vo);
}
