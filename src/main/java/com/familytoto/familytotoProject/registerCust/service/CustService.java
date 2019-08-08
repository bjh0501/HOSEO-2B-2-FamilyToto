package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustService {
	void insertCust(CustVO vo, HttpServletRequest request);
}