package com.familytoto.familytotoProject.registerCust.service;

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
	public void insertCust(CustVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		
		custDao.insert(vo);		
	}
}
