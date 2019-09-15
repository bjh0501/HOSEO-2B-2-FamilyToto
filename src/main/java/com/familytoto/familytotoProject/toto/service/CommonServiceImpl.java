package com.familytoto.familytotoProject.toto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	CommonDao commonDao;
	
	@Override
	public CreditVO getCustCredit(CustVO vo) {
		return commonDao.getCustCredit(vo);
	}
	
}
