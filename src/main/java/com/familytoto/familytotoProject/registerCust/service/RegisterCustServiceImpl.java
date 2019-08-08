package com.familytoto.familytotoProject.registerCust.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.registerCust.dao.RegisterCustDAO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Service
public class RegisterCustServiceImpl implements RegisterCustService {
	
	@Autowired
	RegisterCustDAO registerCustDao; 
	
	@Override
	public int insertRegisterCust(RegisterCustVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		
		return registerCustDao.insert(vo);
	}
}
