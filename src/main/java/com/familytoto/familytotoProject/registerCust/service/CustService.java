package com.familytoto.familytotoProject.registerCust.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustService {
	void insertCust(CustVO vo, HttpServletRequest request);
	Map<String, Object> checkCust(CustVO sId);
}	