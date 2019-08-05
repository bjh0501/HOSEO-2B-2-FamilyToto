package com.familytoto.familytotoProject.mybatis.webLog.mapper;

import java.util.List;

import com.familytoto.familytotoProject.mybatis.webLog.domain.WebLogVO;

public interface WebLogMapper {
	
	int insertWebLog(WebLogVO webLogVO);
	
	List<WebLogVO> getWebLogList();

}