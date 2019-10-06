package com.familytoto.familytotoProject.toto.service;



import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.RulletVO;

public interface RulletService {
	// 배팅시작
	int insertRulletBet(RulletVO vo, CreditVO creVo, HttpServletRequest request);
	
	// 결과
	int updateRulletEnd(RulletVO vo, HttpServletRequest request);
	
	// 배팅도중 팅기고 다시 들어올경우 패배처리
	int updateInitialRullet(int familyCustNo);
	
	// 룰렛 축적된돈 불러오기
	int getAccumCredit();
}