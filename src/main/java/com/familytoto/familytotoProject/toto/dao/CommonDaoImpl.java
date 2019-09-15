package com.familytoto.familytotoProject.toto.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class CommonDaoImpl implements CommonDao {
	@Autowired
	SqlSession sqlSession;
	
	public CreditVO getCustCredit(CustVO vo) {
		return sqlSession.selectOne("totoMiniGame.getCustCredit", vo);
	}
	
}
