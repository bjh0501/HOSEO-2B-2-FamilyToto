package com.familytoto.familytotoProject.registerCust.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class CustDAOImpl implements CustDAO{
	@Autowired
	SqlSession SqlSession;

	@Override
	public void insert(CustVO vo) {
		SqlSession.insert("registerCust.insertCust", vo);
	}
	
	public Map<String, Object> checkId(CustVO sId) {
		return SqlSession.selectOne("registerCust.checkId", sId);
	}
}
