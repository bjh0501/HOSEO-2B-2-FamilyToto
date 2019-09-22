package com.familytoto.familytotoProject.charge.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class ChargeDaoImpl implements ChargeDao{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<String, Object> getCreditInfo(CustVO vo) {
		return sqlSession.selectOne("charge.listChargeCnt", vo);
	}

	@Override
	public int doCharge(CreditVO vo) {
		return sqlSession.insert("charge.insertCharge", vo);
	}

	@Override
	public Map<String, Object> todayCharge(CreditVO vo) {
		return sqlSession.selectOne("charge.todayCharge",vo);
	}

	@Override
	public int getCurrentCredit(int familyCustNo) {
		return sqlSession.selectOne("charge.getCurrentCredit",familyCustNo);
	}
}
