package com.familytoto.familytotoProject.toto.service;

import java.util.List;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

public interface SportsTotoService {
	// 토토 구입,곧삭제예정
	int insertBuyToto(SportsBettingVO vo, CreditVO creVo, SportsVO sVo);
	
	// 토토구입 묶음
	int insertSportsBettingGroup(SportsBettingVO vo);
	
	// 해당 종목 배당get
	double getSportsMatchBet(SportsVO vo);
	
	// 토토 구입
	int insertSportsBetting(SportsBettingVO vo);
	
	// 배팅그룹에 이기면 받을 실제 배팅배율 업데이트
	int updateSportsGrpSumBet(SportsBettingVO vo);
	
	// 배팅했는지 체크
	boolean isDupleBet(SportsBettingVO vo);
	
	// 국내축구 리스트
	List<SportsVO> listSportsTotoInnerSoccer();
}
