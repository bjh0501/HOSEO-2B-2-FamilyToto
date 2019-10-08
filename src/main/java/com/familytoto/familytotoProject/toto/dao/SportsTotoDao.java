package com.familytoto.familytotoProject.toto.dao;

import java.util.List;

import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

public interface SportsTotoDao {
	
	// 토토구입 묶음
	int insertSportsBettingGroup(SportsBettingVO vo);
	
	// 토토 구입
	int insertSportsBetting(SportsBettingVO vo);
	
	// 해당 종목 배당get
	double getSportsMatchBet(SportsVO vo);
	
	// 배팅그룹에 이기면 받을 실제 배팅배율 업데이트
	int updateSportsGrpSumBet(SportsBettingVO vo);
	
	// 배팅했는지 체크
	boolean isDupleBet(SportsBettingVO vo);
	
	// 국내축구 리스트
	List<SportsVO> listSportsTotoInnerSoccer(int familyCustNo);
	
	// 구입한 스포츠번호 리스트
	List<SportsVO> listBoughtSportsNo(int familyCustNo);
	
	// 경기시작전인지 체크
	boolean isCanSportsTotoSChedule(SportsBettingVO vo);
}
