package com.familytoto.familytotoProject.registerCust.dao;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CustDAO {
	public int insert(CustVO vo);
	public Map<String, Object> checkId(CustVO sId);
}
