package com.familytoto.familytotoProject.vip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.vip.dao.VipDao;
import com.familytoto.familytotoProject.vip.domain.VipVO;

@Service
public class VipServiceImpl implements VipService {
	@Autowired
	VipDao vipDao;
	
	@Override
	public int insertGameRoom(VipVO vo) {
		if(vipDao.insertGameRoom(vo) != 1) {
			throw new RuntimeException("게임방만들기 에러");
		}
		
		return vo.getVipGameNo();
	}

	@Override
	public int insertGamePlayers(VipVO vo) {
		return vipDao.insertGamePlayers(vo);
	}

	@Override
	public int insertVipBet(CreditVO vo) {
		if(vipDao.insertVipBet(vo) != 1) {
			throw new RuntimeException("VIP룸 크레딧 배팅 실패");
		}
		
		return vo.getCreditId();
	}

	@Override
	public boolean isVipCreditValue(CreditVO vo) {
		return vipDao.isVipCreditValue(vo);
	}

	@Override
	public int getVipGamePlayersCnt(int vipGameNo) {
		return vipDao.getVipGamePlayersCnt(vipGameNo);
	}

	@Override
	public int insertVipWinnerCredit(CreditVO vo) {
		return vipDao.insertVipWinnerCredit(vo);
	}

	@Override
	public int updateCloseGameRoom(int vipGameNo) {
		return vipDao.updateCloseGameRoom(vipGameNo);
	}

	@Override
	public boolean isFamilyCustVip(int familyCustNo) {
		return vipDao.isFamilyCustVip(familyCustNo);
	}
}
