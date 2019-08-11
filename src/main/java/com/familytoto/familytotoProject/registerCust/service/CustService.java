package com.familytoto.familytotoProject.registerCust.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustService {
	int insertCust(CustVO vo, HttpServletRequest request);
	Map<String, Object> checkId(CustVO sId);
}	