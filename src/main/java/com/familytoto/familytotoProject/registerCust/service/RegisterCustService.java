package com.familytoto.familytotoProject.registerCust.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface RegisterCustService {
	int insertRegisterCust(RegisterCustVO vo, HttpServletRequest request);
	Map<String, Object> checkNickname(RegisterCustVO vo);
	int insertRecommend(RegisterCustVO vo);
	Map<String, Object> checkRecommend(CustVO vo);
}