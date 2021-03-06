package com.familytoto.familytotoProject.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class CustLoginDAOImpl implements CustLoginDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public CustVO login(CustVO vo) {
		return sqlSession.selectOne("login.custLogin", vo);
	}

	@Override
	public CustVO getExpInfo(int familyCustNo) {
		return sqlSession.selectOne("login.geExpInfo", familyCustNo);
	}

	@Override
	public List<String> listPreferProduct(int familyCustNo) {
		return sqlSession.selectList("login.listPreferProduct", familyCustNo);
	}

	@Override
	public int updateVipticketExpire(ExpVO vo) {
		return sqlSession.update("login.updateVipticketExpire", vo);
	}

	@Override
	public int updateVipTicketExpireExp(int familyCustNo) {
		return sqlSession.update("login.updateVipTicketExpireExp", familyCustNo);
	}
}
