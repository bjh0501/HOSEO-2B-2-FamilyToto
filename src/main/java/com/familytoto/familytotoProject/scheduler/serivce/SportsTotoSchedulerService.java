package com.familytoto.familytotoProject.scheduler.serivce;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface SportsTotoSchedulerService {
	// 국내축구
	public void inSoccer(String gubun, String matchYear,
			String matchMonth,
			HttpServletRequest request,
			CustVO custVo);
}
