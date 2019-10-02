package com.familytoto.familytotoProject.scheduler.serivce;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface SportsTotoSchedulerService {
	// 국내축구
	public void inSoccer();
	
	// 결과 처리
	public int sportsResult(CustVO cVo, String creditState,
			HttpServletRequest request,
			int sportsBettingGroupNo);
}
