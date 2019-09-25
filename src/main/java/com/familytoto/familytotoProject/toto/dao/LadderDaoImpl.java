package com.familytoto.familytotoProject.toto.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

@Repository
public class LadderDaoImpl implements LadderDao {
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	ExpService expService;
	
	@Override
	public int insertLadderBet(LadderVO vo) {
		
		return sqlSession.insert("totoMiniGame.insertLadderBet", vo);
	}

	@Override
	public int insertLadderCredit(CreditVO vo) {
		return sqlSession.insert("charge.insertCharge", vo);
	}
}
