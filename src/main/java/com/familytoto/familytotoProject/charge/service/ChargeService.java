package com.familytoto.familytotoProject.charge.service;

import java.util.Map;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ChargeService {
	Map<String, Object> getCreditInfo(CustVO vo);
	int doCharge(CreditVO vo);
}
