package com.familytoto.familytotoProject.toto.dao;

import com.familytoto.familytotoProject.toto.domain.RulletStorageVO;
import com.familytoto.familytotoProject.toto.domain.RulletVO;

public interface RulletDao {
	// 배팅시작
	int insertRulletBet(RulletVO vo);
	
	// 배팅도중 팅기고 다시 들어올경우 패배처리
	int updateInitialRullet(int familyCustNo);
	
	// 배팅결과
	int updateRulletEnd(RulletVO vo);
	
	// 룰렛 돈 축적
	int insertRulletStorage(RulletStorageVO vo);
	
	// 룰렛 축적된돈 불러오기
	int getAccumCredit();
	
	// 룰렛 돈 업데이트
	int updateRulletAccumCredit(RulletStorageVO vo);
}
