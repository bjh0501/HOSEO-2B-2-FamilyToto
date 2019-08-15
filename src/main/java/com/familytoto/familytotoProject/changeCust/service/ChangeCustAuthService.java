package com.familytoto.familytotoProject.changeCust.service;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ChangeCustAuthService {
	
	// 새 연동
	int updateAuthNaver(Map<String, Object> map);
	
	// 연동해제
	int updateUnAuthNaver(CustVO vo);
	
	// 연동 하러하는데 다른계정에 연동된경우 
	Map<String, Object> getOtherAccountAuthNaver(Map<String, Object> map);
}
