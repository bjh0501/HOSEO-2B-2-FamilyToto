package com.familytoto.familytotoProject.toto.service;

import java.util.Map;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.DiceVO;

public interface DiceService {
	//  배팅
	Map<String, Object> insertDiceBet(DiceVO vo, CreditVO creVo);
}
