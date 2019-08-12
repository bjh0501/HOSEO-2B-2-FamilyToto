package com.familytoto.familytotoProject.changeCust.service;

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
		Map<String, Object> map = changeCustDAO.getCustInfo(cVo);
		String email = (String) map.get("familyCustEmail");
		String emailSplit[] = email.split("@");
		String email1 = emailSplit[0];
		String email2 = emailSplit[1];
		
		map.put("email1", email1);
		map.put("email2", email2);
		
		return map;
	}

	@Override
	public int updateCustInfo(CustVO cVo, RegisterCustVO rVo) {
		return changeCustDAO.updateCustInfo(cVo, rVo);
	}
}
