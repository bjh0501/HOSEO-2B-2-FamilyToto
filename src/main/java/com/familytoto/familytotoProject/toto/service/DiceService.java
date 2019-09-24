package com.familytoto.familytotoProject.toto.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.DiceVO;

public interface DiceService {
	//  배팅
	Map<String, Object> insertDiceBet(DiceVO vo,
			CreditVO creVo,
			HttpServletRequest request,
			HttpSession session);
}
