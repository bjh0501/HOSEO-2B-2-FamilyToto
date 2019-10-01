package com.familytoto.familytotoProject.exp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.exp.dao.ExpDao;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class ExpServiceImpl implements ExpService {
	@Autowired
	ExpDao expDao;
	
	@Override
	@Transactional
	public int insertExp(CustVO cVo,
			String expState,
			int expValue,
			HttpServletRequest request) {
		ExpVO eVo = new ExpVO();
		
		if(!expState.equals("GGS") &&
				!expState.equals("GGW") &&
				!expState.equals("DGS") &&
				!expState.equals("DGW") &&
				!expState.equals("RGW") &&
				!expState.equals("RGS") &&
				!expState.equals("LGW") &&
				!expState.equals("LGS")) {
			return -1;
		}
		
		eVo.setFamilyCustNo(cVo.getFamilyCustNo());
		eVo.setExpState(expState);
		eVo.setExpValue(expValue);
		eVo.setRegCustNo(cVo.getCustNo());
		eVo.setRegIp(request.getRemoteAddr());
		
		expDao.updateCustExp(eVo);
		
		return expDao.insertExp(eVo);
	}

	@Override
	public ExpVO getLevelInfo(int familyCustNo) {
		return expDao.getLevelInfo(familyCustNo);
	}
}
