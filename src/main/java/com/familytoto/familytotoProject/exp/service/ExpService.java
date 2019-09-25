package com.familytoto.familytotoProject.exp.service;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ExpService {
	// 경험치 획득
	int insertExp(CustVO cVo,
			String expState,
			int expValue,
			HttpServletRequest request);
	
	// 회원 경험치정보 가져오기(헤더에쓰임)
	ExpVO getLevelInfo(int familyCustNo);
}
