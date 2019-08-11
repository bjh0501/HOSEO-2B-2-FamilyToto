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
	
	@Override
	public int insertCust(CustVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		
		String sPassword = vo.getCustPassword();	
		String sGeneratorPassword = vo.toEncodePassword(sPassword);
		vo.setCustPassword(sGeneratorPassword);		
		
		return custDao.insert(vo); 
	}

	@Override
	public Map<String, Object> checkId(CustVO sId) {
		return custDao.checkId(sId);
	}
}
