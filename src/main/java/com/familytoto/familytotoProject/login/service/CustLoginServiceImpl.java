package com.familytoto.familytotoProject.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.exp.domain.ExpVO;
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

	@Override
	public CustVO getExpInfo(int familyCustNo) {
		return null;
	}

	@Override
	public List<String> listPreferProduct(int familyCustNo) {
		return custLoginDao.listPreferProduct(familyCustNo);
	}

	@Override
	@Transactional
	public int updateVipticketExpire(ExpVO vo) {
		
		int isExp = custLoginDao.updateVipticketExpire(vo);
		
		if(custLoginDao.updateVipticketExpire(vo) >= 2) {
			throw new RuntimeException("VIP 만료 에러");
		}
		
		if(isExp == 1) {
			if(custLoginDao.updateVipTicketExpireExp(vo.getFamilyCustNo()) != 1) {
				throw new RuntimeException("VIP 만료 경험치 갱신 에러");
			}
		}
			
		return 1;
	}

}
