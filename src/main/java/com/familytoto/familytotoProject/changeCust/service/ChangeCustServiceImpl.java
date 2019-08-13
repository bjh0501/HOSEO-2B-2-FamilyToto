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
		String email1 = "";
		String email2 = "";
		
		if(emailSplit.length == 2) {
			email1 = emailSplit[0];
			email2 = emailSplit[1];
		} else if(emailSplit.length == 1) {
			email1 = emailSplit[0];
		}
		
		map.put("email1", email1);
		map.put("email2", email2);
		
		return map;
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
