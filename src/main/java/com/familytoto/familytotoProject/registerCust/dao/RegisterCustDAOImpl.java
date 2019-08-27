package com.familytoto.familytotoProject.registerCust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.domain.ZipcodeVO;

@Repository
public class RegisterCustDAOImpl implements RegisterCustDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insert(RegisterCustVO vo) {
		return sqlSession.insert("registerCust.insertRegisterCust", vo);
	}

	@Override
	public Map<String, Object> checkNickname(RegisterCustVO vo) {
		return sqlSession.selectOne("registerCust.checkNickname", vo);
	}

	@Override
	public int insertRecommend(RegisterCustVO vo) {
		return sqlSession.insert("registerCust.insertRecommend", vo);
	}

	@Override
	public Map<String, Object> checkRecommend(CustVO vo) {
		return sqlSession.selectOne("registerCust.checkId", vo);
	}

	@Override
	public List<ZipcodeVO> listSido() {
		return sqlSession.selectList("registerCust.listSido");
	}

	@Override
	public List<ZipcodeVO> listGugun(String sido) {
		return sqlSession.selectList("registerCust.listGugun", sido);
	}

	@Override
	public List<ZipcodeVO> listDong(ZipcodeVO vo) {
		return sqlSession.selectList("registerCust.listDong", vo);
	}

	@Override
	public List<ZipcodeVO> listZip(ZipcodeVO vo) {
		return sqlSession.selectList("registerCust.listZip", vo);
	}

	@Override
	public Map<String, Object> getUsedId(CustVO vo) {
		return sqlSession.selectOne("registerCust.checkId", vo);
	}
}
