package com.familytoto.familytotoProject.login.dao;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustLoginDAO {
	Map<String, Object> login(CustVO vo);
}
