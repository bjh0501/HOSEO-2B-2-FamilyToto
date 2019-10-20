package com.familytoto.familytotoProject.charge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.dao.ItemShopDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class ItemShopServiceImpl implements ItemShopService {
	@Autowired
	ItemShopDao itemShopDao; 
	
	@Override
	public boolean checkBuyCharge(CreditVO vo) {
		return itemShopDao.checkBuyCharge(vo);
	}
	
	@Override
	public int insertCredit(CreditVO vo) {
		return itemShopDao.insertCredit(vo);
	}

	@Override
	public boolean isCheckNickname(String nickname) {
		return itemShopDao.isCheckNickname(nickname);
	}

	@Override
	public int updateNickname(CustVO cVo) {
		return itemShopDao.updateNickname(cVo);
	}

	@Override
	public int insertVipTicket(ExpVO vo) {
		return itemShopDao.insertVipTicket(vo);
	}

	@Override
	public boolean isVipTicket(int familyCustNo) {
		return itemShopDao.isVipTicket(familyCustNo);
	}

	@Override
	public int updateVipExp(int familyCustNo) {
		return itemShopDao.updateVipExp(familyCustNo);
	}
}
