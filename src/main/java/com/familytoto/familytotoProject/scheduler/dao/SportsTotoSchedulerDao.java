package com.familytoto.familytotoProject.scheduler.dao;

import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;

public interface SportsTotoSchedulerDao {
	// 국내축구 insert
	public int inSoccer(TotoSportsVO vo);
	
	// 토토 결과보기
	public int updateSportsToto(TotoSportsVO vo);
	
	// not 승리자 체크(sum때문에 이렇게처리)
	public boolean isNotSportsTotoWinnerCheck(int familyCustNo);
	
	// 스포츠 토토  땃느지 체크
	public SportsBettingVO isSportsTotoWin(int familyCustNo);
	
	// 사용자 배팅 결과 업데이트
	public int updateCustSportsTotoResult(SportsBettingVO vo);
	
	// 그룹배팅에 맞는 크레딧 값 가져오기
	public int getCreditValueByBettingGroup(SportsBettingVO vo);
}
