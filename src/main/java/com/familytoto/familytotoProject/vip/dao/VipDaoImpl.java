package com.familytoto.familytotoProject.vip.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.vip.domain.VipVO;

@Repository
public class VipDaoImpl implements VipDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertGameRoom(VipVO vo) {
		return sqlSession.insert("vip.insertGameRoom", vo);
	}

	@Override
	public int insertGamePlayers(VipVO vo) {
		return sqlSession.insert("vip.insertGamePlayers", vo);
	}

	@Override
	public int insertVipBet(CreditVO vo) {
		return sqlSession.insert("vip.insertVipBet", vo);
	}

	@Override
	public boolean isVipCreditValue(CreditVO vo) {
		return sqlSession.selectOne("vip.isVipCreditValue", vo);
	}

	@Override
	public int getVipGamePlayersCnt(int vipGameNo) {
		return sqlSession.selectOne("vip.getVipGamePlayersCnt", vipGameNo);
	}

	@Override
	public int insertVipWinnerCredit(CreditVO vo) {
		return sqlSession.insert("vip.insertVipWinnerCredit", vo);
	}

	@Override
	public int updateCloseGameRoom(int vipGameNo) {
		return sqlSession.update("vip.updateCloseGameRoom", vipGameNo);
	}

	@Override
	public boolean isFamilyCustVip(int familyCustNo) {
		return sqlSession.selectOne("vip.isFamilyCustVip", familyCustNo);
	}

	@Override
	public boolean isBettingCredit(VipVO vo) {
		return sqlSession.selectOne("vip.isBettingCredit", vo);
	}

	@Override
	public int insertBettingCredit(CreditVO vo) {
		return sqlSession.insert("vip.insertBettingCredit", vo);
	}

	@Override
	public int getHaveTotalCredit(int familyCustNo) {
		return sqlSession.selectOne("vip.getHaveTotalCredit", familyCustNo);
	}
}
