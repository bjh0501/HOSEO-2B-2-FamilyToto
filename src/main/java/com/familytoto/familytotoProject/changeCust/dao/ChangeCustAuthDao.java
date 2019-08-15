package com.familytoto.familytotoProject.changeCust.dao;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ChangeCustAuthDao {
	
	// 네이버 
	Map<String, Object> checkNaver(Map<String, Object> map);
	
	int authNaver(Map<String, Object> map);
	
	// 네이버 연동해제
	int unAuthNaver(CustVO vo);
	
}
