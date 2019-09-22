package com.familytoto.familytotoProject.toto.dao;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.GraphVO;

public interface GraphDao {
	// 겜시작전 지난번에 비정상적으로 튕긴경우 패배처리
	int updateLastGamePorcess(CustVO vo);
	
	// 그래프게임 시작
	int insertGraphGame(GraphVO vo);
	
	// 정상적인 게임인지 확인하는로직
	boolean isProcessGame(GraphVO vo);
	
	// 게임패배 기록
	int updateRecordLose(GraphVO vo);
	
	// 크레딧소모
	int insertCredit(CreditVO vo);
	
	// 배팅 멈춤 유효성검사
	boolean isCorrectStopGraph(GraphVO vo);
	
	// 배팅 성공한경우
	int insertGetBet(CreditVO vo);
	
	// 배팅 멈춤 로직
	int updateStopGraph(GraphVO vo);
	
}
