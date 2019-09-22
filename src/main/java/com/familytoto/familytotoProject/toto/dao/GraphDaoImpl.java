package com.familytoto.familytotoProject.toto.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.GraphVO;
import com.google.api.services.storage.Storage.BucketAccessControls.Insert;

@Repository
public class GraphDaoImpl implements GraphDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertGraphGame(GraphVO vo) {
		return sqlSession.insert("totoMiniGame.insertGraphGame", vo);
	}

	@Override
	public boolean isProcessGame(GraphVO vo) {
		return sqlSession.selectOne("totoMiniGame.isProcessGame", vo);
	}

	@Override
	public int updateRecordLose(GraphVO vo) {
		return sqlSession.update("totoMiniGame.updateRecordLose", vo);
	}

	@Override
	public int updateLastGamePorcess(CustVO vo) {
		return sqlSession.update("totoMiniGame.updateLastGamePorcess", vo);
	}

	@Override
	public int insertCredit(CreditVO vo) {
		return sqlSession.insert("charge.insertCharge", vo);
	}

	@Override
	public boolean isCorrectStopGraph(GraphVO vo) {
		return sqlSession.selectOne("totoMiniGame.isCorrectStopGraph", vo);
	}

	@Override
	public int insertGetBet(CreditVO vo) {
		return sqlSession.insert("charge.insertCharge", vo);
	}

	@Override
	public int updateStopGraph(GraphVO vo) {
		return sqlSession.update("totoMiniGame.updateStopGraph", vo);
	}
}
