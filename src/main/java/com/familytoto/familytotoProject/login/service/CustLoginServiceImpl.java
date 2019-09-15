package com.familytoto.familytotoProject.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.login.dao.CustLoginDAO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class CustLoginServiceImpl implements CustLoginService {
	@Autowired
	CustLoginDAO custLoginDao;
	
	@Override
	public CustVO login(CustVO vo) {
		CustVO custLogin = custLoginDao.login(vo);
		
		if(custLogin != null) { // 아디 없으면 null
			String getDBPass = (String) custLogin.getCustPassword();
			
			if(vo.isDecodePassword(vo,getDBPass) == false) {
				return null;
			}
		} else { // 아디가 없는경우
			return null;
		}
		
		return custLoginDao.login(vo);
	}

}
