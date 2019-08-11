package com.familytoto.familytotoProject.findIdPw.dao;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface FindIdPwDao {
	List<Map<String, Object>> findId(RegisterCustVO vo);
	Map<String, Object> findPassword(String id, String email);
	int setPassword(String sId, String sPassword);
}
