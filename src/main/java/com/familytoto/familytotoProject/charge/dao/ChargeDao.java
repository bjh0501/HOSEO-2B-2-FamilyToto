package com.familytoto.familytotoProject.charge.dao;

import java.util.Map;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface ChargeDao {
	Map<String, Object> getCreditInfo(CustVO vo);
	int doCharge(CreditVO vo);
	Map<String, Object> todayCharge(CreditVO vo);
}
