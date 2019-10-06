package com.familytoto.familytotoProject.toto.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.toto.domain.RulletStorageVO;
import com.familytoto.familytotoProject.toto.domain.RulletVO;

@Repository
public class RulletDaoImpl implements RulletDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insertRulletBet(RulletVO vo) {
		return sqlSession.insert("totoMiniGame.insertRulletBet", vo);
	}

	@Override
	public int updateInitialRullet(int familyCustNo) {
		return sqlSession.update("totoMiniGame.updateInitialRullet", familyCustNo);
	}

	@Override
	public int updateRulletEnd(RulletVO vo) {
		return sqlSession.update("totoMiniGame.updateRulletEnd", vo);
	}

	@Override
	public int insertRulletStorage(RulletStorageVO vo) {
		return sqlSession.insert("totoMiniGame.insertRulletStorage", vo);
	}

	@Override
	public int getAccumCredit() {
		return sqlSession.selectOne("totoMiniGame.getAccumCredit");
	}

	@Override
	public int updateRulletAccumCredit(RulletStorageVO vo) {
		return sqlSession.update("totoMiniGame.updateRulletAccumCredit", vo);
	}
}
