package com.familytoto.familytotoProject.registerCust.dao;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface RegisterCustDAO {
	public int insert(RegisterCustVO vo);
	public Map<String, Object> checkNickname(RegisterCustVO vo);
}
