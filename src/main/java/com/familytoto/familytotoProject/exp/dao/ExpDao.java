package com.familytoto.familytotoProject.exp.dao;

import com.familytoto.familytotoProject.exp.domain.ExpVO;

public interface ExpDao {
	// 경험치 획득
	int insertExp(ExpVO vo);
	
	// 회원 경험치 업데이트
	int updateCustExp(ExpVO vo);
}
