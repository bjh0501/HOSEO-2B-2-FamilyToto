package com.familytoto.familytotoProject.exp.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.exp.domain.ExpVO;

@Repository
public class ExpDaoImpl implements ExpDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertExp(ExpVO vo) {
		return sqlSession.insert("exp.insertExp", vo);
	}

	@Override
	public int updateCustExp(ExpVO vo) {
		return sqlSession.update("exp.updateCustExp", vo);
	}

	@Override
	public ExpVO getLevelInfo(int familyCustNo) {
		return sqlSession.selectOne("exp.getCustLevel", familyCustNo);
	}
}
