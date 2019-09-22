package com.familytoto.familytotoProject.toto.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.domain.DiceVO;

@Repository
public class DiceDaoImpl implements DiceDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertDiceBet(DiceVO vo) {
		return sqlSession.insert("totoMiniGame.insertDiceBet",vo);
	}

	@Override
	public int insertCredit(CreditVO vo) {
		return sqlSession.insert("charge.insertCharge",vo);
	}
}
