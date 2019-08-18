package com.familytoto.familytotoProject.login.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class SocialDaoImpl implements SocialDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertSocial(SocialVO vo) {
		return sqlSession.insert("registerCust.insertSocial",vo);
	}

	@Override
	public Map<String, Object> checkSocial(SocialVO vo) {
		return sqlSession.selectOne("registerCust.checkSocial", vo);
	}

	@Override
	public CustVO getSocialFamilyNo(SocialVO vo) {
		return sqlSession.selectOne("login.socialLogin", vo);
	}
	
	public CustVO getsocialLoginNoFamilyNo(SocialVO vo) {
		return sqlSession.selectOne("login.socialLoginNoFamilyNo", vo);
	}
}
