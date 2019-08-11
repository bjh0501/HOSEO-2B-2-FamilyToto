package com.familytoto.familytotoProject.login.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.login.dao.CustLoginDAO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Service
public class CustLoginServiceImpl implements CustLoginService {
	@Autowired
	CustLoginDAO custLoginDao;
	
	@Override
	public Map<String, Object> login(CustVO vo) {
		Map<String, Object> custLogin = custLoginDao.login(vo);
		
		if(custLogin != null) { // 아디 없으면 null
			String getDBPass = (String) custLoginDao.login(vo).get("custPassword");
			
			// 
			System.out.println(vo.getCustPassword() + "==" + getDBPass);
			if(vo.isDecodePassword(vo,getDBPass) == false) {
				return null;
			}
		} else { // 아디가 없는경우
			return null;
		}
		
		return custLoginDao.login(vo);
	}

}
