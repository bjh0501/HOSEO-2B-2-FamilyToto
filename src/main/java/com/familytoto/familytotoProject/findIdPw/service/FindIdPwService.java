package com.familytoto.familytotoProject.findIdPw.service;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface FindIdPwService {
	List<Map<String, Object>> findId(RegisterCustVO vo);
	int findPassword(RegisterCustVO rcVo, CustVO cVo);
	int setPassword(String sId, String sPassword);
}
