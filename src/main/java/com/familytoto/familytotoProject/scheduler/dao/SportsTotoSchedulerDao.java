package com.familytoto.familytotoProject.scheduler.dao;

import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;

public interface SportsTotoSchedulerDao {
	// 국내축구 insert
	public int inSoccer(TotoSportsVO vo);
	
	// 토토 결과보기
	public int updateSportsToto(TotoSportsVO vo);
	
	// not 승리자 체크(sum때문에 이렇게처리)
	public boolean isNotSportsTotoWinnerCheck(int familyCustNo);
}
