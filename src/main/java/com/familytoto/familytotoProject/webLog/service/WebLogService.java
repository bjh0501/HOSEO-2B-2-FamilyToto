package com.familytoto.familytotoProject.webLog.service;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.webLog.domain.WebLogVO;

public interface WebLogService {
	
	void insertWebLog(WebLogVO vo, HttpServletRequest request);
	
	//List<WebLogVO> getWebLogList();

}