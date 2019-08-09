package com.familytoto.familytotoProject.registerCust.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Repository
public class RegisterCustDAOImpl implements RegisterCustDAO{
	@Autowired
	SqlSession SqlSession;

	@Override
	public int insert(RegisterCustVO vo) {
		return SqlSession.insert("registerCust.insertRegisterCust", vo);
	}

	@Override
	public Map<String, Object> checkNickname(RegisterCustVO vo) {
		return SqlSession.selectOne("registerCust.checkNickname", vo);
	}
}
