package com.familytoto.familytotoProject.changeCust.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.login.domain.SocialVO;

@Repository
public class ChangeCustAuthDaoImpl implements ChangeCustAuthDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<String, Object> checkSocial(SocialVO vo) {
		return sqlSession.selectOne("changeCust.check", vo);
	}

	@Override
	public int authSocial(SocialVO vo) {
		return sqlSession.update("changeCust.auth", vo);
	}

	@Override
	public int unAuthSocial(SocialVO vo) {
		return sqlSession.update("changeCust.unAuth", vo);
	}

	@Override
	public int insertAuthSocial(SocialVO vo) {
		return sqlSession.update("registerCust.insertAuthSocial", vo);
	}

}
