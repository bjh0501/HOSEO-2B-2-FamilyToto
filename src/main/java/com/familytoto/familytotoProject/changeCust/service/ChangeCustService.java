package com.familytoto.familytotoProject.changeCust.service;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface ChangeCustService {
	Map<String, Object> getCustInfo(CustVO cVo);
	int updateCustInfo(CustVO cVo, RegisterCustVO rVo);
	int updateDropCust(CustVO rVo);
	int updateDropFamilyCust(CustVO rVo);
	int updateInterAuth(CustVO rVo);
	Map<String, Object> getCheckPassword(CustVO cVo);
	
}