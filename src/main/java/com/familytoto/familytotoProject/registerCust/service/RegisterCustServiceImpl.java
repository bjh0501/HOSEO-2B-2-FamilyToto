package com.familytoto.familytotoProject.registerCust.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.registerCust.dao.RegisterCustDAO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
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

	@Override
	public Map<String, Object> checkNickname(RegisterCustVO vo) {
		// TODO Auto-generated method stub
		return registerCustDao.checkNickname(vo);
	}

	@Override
	public int insertRecommend(RegisterCustVO vo) {
		return registerCustDao.insertRecommend(vo);
	}

	@Override
	public Map<String, Object> checkRecommend(CustVO vo) {
		// TODO Auto-generated method stub
		return registerCustDao.checkRecommend(vo);
	}
}
