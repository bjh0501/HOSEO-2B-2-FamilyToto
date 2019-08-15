package com.familytoto.familytotoProject.changeCust.dao;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface ChangeCustDAO {
	List<Map<String, Object>> getCustInfo(CustVO cVo);
	int updateCustInfo(CustVO cVo, RegisterCustVO rVo);
	int updateDropCust(CustVO rVo);
	int updateDropFamilyCust(CustVO rVo);
	Map<String, Object> getCheckPassword(CustVO cVo);
}
