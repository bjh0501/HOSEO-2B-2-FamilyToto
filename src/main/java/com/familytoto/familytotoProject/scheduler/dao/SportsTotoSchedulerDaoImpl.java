package com.familytoto.familytotoProject.scheduler.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;

@Repository
public class SportsTotoSchedulerDaoImpl implements SportsTotoSchedulerDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int inSoccer(TotoSportsVO vo) {
		return sqlSession.insert("totoSports.insertSportsToto", vo);
	}

	@Override
	public int updateSportsToto(TotoSportsVO vo) {
		return sqlSession.update("totoSports.updateSportsToto", vo);
	}

	@Override
	public boolean isNotSportsTotoWinnerCheck(int familyCustNo) {
		return sqlSession.selectOne("totoSports.isNotSportsTotoWinnerCheck", familyCustNo);
	}

	@Override
	public SportsBettingVO isSportsTotoWin(int familyCustNo) {
		return sqlSession.selectOne("totoSports.isSportsTotoWin", familyCustNo);
	}

	@Override
	public int updateCustSportsTotoResult(SportsBettingVO vo) {
		return sqlSession.update("totoSports.updateCustSportsTotoResult", vo);
	}

	@Override
	public int getCreditValueByBettingGroup(SportsBettingVO vo) {
		return sqlSession.selectOne("totoSports.getCreditValueByBettingGroup", vo);
	}
}
