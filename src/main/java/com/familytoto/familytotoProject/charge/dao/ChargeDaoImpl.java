package com.familytoto.familytotoProject.charge.dao;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class ChargeDaoImpl implements ChargeDao{
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	Environment env;
	
	private String bucketName;
	
	@PostConstruct
	public void init() {
		bucketName = env.getProperty("aws.s3.bucketName");
	}
	
	
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

	@Override
	public CreditVO getCardInfo(int familyCustNo) {
		return sqlSession.selectOne("charge.getCardInfo",familyCustNo);
	}

	@Override
	public boolean isCardChargeCheck(int familyCustNo) {
		return sqlSession.selectOne("charge.isCardChargeCheck",familyCustNo);
	}
}
