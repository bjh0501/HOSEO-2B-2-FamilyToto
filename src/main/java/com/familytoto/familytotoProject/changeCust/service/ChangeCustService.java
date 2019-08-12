package com.familytoto.familytotoProject.changeCust.service;

import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface ChangeCustService {
	Map<String, Object> getCustInfo(CustVO cVo);
	int updateCustInfo(CustVO cVo, RegisterCustVO rVo);
}
