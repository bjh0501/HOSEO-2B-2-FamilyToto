package com.familytoto.familytotoProject.registerCust.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.registerCust.dao.CustDAO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class CustServiceImpl implements CustService {
	
	@Autowired
	CustDAO custDao; 
	
	@Autowired
	PasswordGeneratorService passwordGeneratorService; 
	
	@Override
	public void insertCust(CustVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		String sPassword = vo.getCustPassword();
		
		// 비밀번호 예외처리
//		if(sPassword.length() < 4 || sPassword.length() > 20) {
//			
//		}
		
		String sGeneratorPassword = passwordGeneratorService.toEncodePassword(sPassword);
		vo.setCustPassword(sGeneratorPassword);
		custDao.insert(vo);		
	}

	@Override
	public Map<String, Object> checkCust(CustVO sId) {
		return custDao.checkId(sId);
	}
}
