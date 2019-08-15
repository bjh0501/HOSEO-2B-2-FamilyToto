package com.familytoto.familytotoProject.changeCust.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class ChangeCustAuthDaoImpl implements ChangeCustAuthDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<String, Object> checkNaver(Map<String, Object> map) {
		return sqlSession.selectOne("changeCust.checkNaver", map);
	}

	@Override
	public int authNaver(Map<String, Object> map) {
		return sqlSession.update("changeCust.authNaver", map);
	}

	@Override
	public int unAuthNaver(CustVO vo) {
		return sqlSession.update("changeCust.unAuthNaver", vo);
	}

}
