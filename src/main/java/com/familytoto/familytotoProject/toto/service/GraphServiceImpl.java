package com.familytoto.familytotoProject.toto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.dao.GraphDao;
import com.familytoto.familytotoProject.toto.domain.GraphVO;

@Service
public class GraphServiceImpl implements GraphService{
	@Autowired
	GraphDao graphDao; 
	
	@Autowired
	CommonDao commonDao;
	
	public int insertGraphGame(GraphVO vo) {
		return graphDao.insertGraphGame(vo);
	}

	@Override
	public int updateRecordLose(GraphVO vo) {
		return graphDao.updateRecordLose(vo);
	}

	@Override
	public int updateLastGamePorcess(CustVO vo) {
		return graphDao.updateLastGamePorcess(vo);
	}

	@Override
	public int insertCredit(CreditVO vo) {
		return graphDao.insertCredit(vo);
	}

	@Override
	public boolean isCorrectStopGraph(GraphVO vo) {
		return graphDao.isCorrectStopGraph(vo);
	}

	@Override
	public int insertGetBet(CreditVO vo) {
		return graphDao.insertGetBet(vo);
	}

	@Override
	public int updateStopGraph(GraphVO vo) {
		return graphDao.updateStopGraph(vo);
	}

	@Override
	public boolean isHaveCredit(CreditVO vo) {
		return commonDao.isHaveCredit(vo);
	}
}
