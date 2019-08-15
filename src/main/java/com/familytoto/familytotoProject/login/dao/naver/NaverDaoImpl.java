package com.familytoto.familytotoProject.login.dao.naver;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NaverDaoImpl implements NaverDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertNaver(Map<String, Object> map) {
		return sqlSession.insert("registerCust.insertNaver", map);
	}

	@Override
	public Map<String, Object> checkNaver(Map<String, Object> map) {
		return sqlSession.selectOne("registerCust.checkNaver", map);
	}
}
