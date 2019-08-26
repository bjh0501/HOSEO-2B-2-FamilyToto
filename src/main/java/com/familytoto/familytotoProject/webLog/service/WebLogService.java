package com.familytoto.familytotoProject.webLog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.webLog.domain.WebLogVO;

public interface WebLogService {
	
	void insertWebLog(WebLogVO vo, HttpServletRequest request, HttpSession session);
	
	//List<WebLogVO> getWebLogList();

}