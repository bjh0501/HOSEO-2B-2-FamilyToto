package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface RegisterCustService {
	int insertRegisterCust(RegisterCustVO vo, HttpServletRequest request);
}