package com.familytoto.familytotoProject.changeCust.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.changeCust.dao.ChangeCustDAO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Service
public class ChangeCustServiceImpl implements ChangeCustService{
	@Autowired
	ChangeCustDAO changeCustDAO;
	
	@Override
	public Map<String, Object> getCustInfo(CustVO cVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = changeCustDAO.getCustInfo(cVo); 
		
		String email = (String) list.get(0).get("familyCustEmail");
		String emailSplit[] = email.split("@");
		String email1 = "";
		String email2 = "";
		
		if(emailSplit.length == 2) {
			email1 = emailSplit[0];
			email2 = emailSplit[1];
		} else if(emailSplit.length == 1) {
			email1 = emailSplit[0];
		}
		
		list.get(0).put("email1", email1);
		list.get(0).put("email2", email2);
		
		// 리스트 맵0에는 회원정보가 다들어있음
		resultMap = list.get(0);
	
		resultMap.put("NA", "");
		resultMap.put("KA", "");
		resultMap.put("FA", "");
		
		for(Map<String, Object> map : list) {
			if(map.containsKey("scCustGubun") && map.containsValue("KA")) resultMap.put("KA", map.get("scCustEmail"));
			if(map.containsKey("scCustGubun") && map.containsValue("NA")) resultMap.put("NA", map.get("scCustEmail"));
			if(map.containsKey("scCustGubun") && map.containsValue("FA")) resultMap.put("FA", map.get("scCustEmail"));
		}
		
		
		return resultMap;
	}

	@Override
	public int updateCustInfo(CustVO cVo, RegisterCustVO rVo) {
		return changeCustDAO.updateCustInfo(cVo, rVo);
	}

	@Override
	public int updateDropCust(CustVO rVo) {
		return changeCustDAO.updateDropCust(rVo);
	}

	@Override
	public int updateDropFamilyCust(CustVO rVo) {
		return changeCustDAO.updateDropFamilyCust(rVo);
	}

	@Override
	public Map<String, Object> getCheckPassword(CustVO cVo) {
		return changeCustDAO.getCheckPassword(cVo); 
	}
}
