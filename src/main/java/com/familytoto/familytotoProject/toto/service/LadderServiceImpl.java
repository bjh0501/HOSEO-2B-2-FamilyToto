package com.familytoto.familytotoProject.toto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.dao.LadderDao;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

@Service
public class LadderServiceImpl implements LadderService {
	@Autowired
	LadderDao ladderDao;
	
	@Override
	public int insertLadderBet(LadderVO vo) {
		return ladderDao.insertLadderBet(vo);
	}

	@Override
	public int insertLadderCredit(CreditVO vo) {
		return ladderDao.insertLadderCredit(vo);
	}
}
