package com.familytoto.familytotoProject.login.service;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustLoginService {
	Map<String, Object> login(CustVO vo);
}
