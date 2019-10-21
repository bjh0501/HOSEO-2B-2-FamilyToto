package com.familytoto.familytotoProject.vip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.vip.domain.VipVO;
import com.familytoto.familytotoProject.vip.model.VipDao;

@Service
public class VipServiceImpl implements VipService {
	@Autowired
	VipDao vipDao;
	
	@Override
	public int insertGameRoom(VipVO vo) {
		return vipDao.insertGameRoom(vo);
	}

	@Override
	public int insertGamePlayers(VipVO vo) {
		return vipDao.insertGamePlayers(vo);
	}
}
