package com.familytoto.familytotoProject.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class CustLoginDAOImpl implements CustLoginDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public CustVO login(CustVO vo) {
		return sqlSession.selectOne("login.custLogin", vo);
	}

}
