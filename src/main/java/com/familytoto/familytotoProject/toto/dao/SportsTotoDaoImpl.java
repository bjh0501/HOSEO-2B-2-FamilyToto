package com.familytoto.familytotoProject.toto.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

@Repository
public class SportsTotoDaoImpl implements SportsTotoDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insertSportsBettingGroup(SportsBettingVO vo) {
		return sqlSession.insert("totoSports.insertSportsBettingGroup", vo);
	}

	@Override
	public int insertSportsBetting(SportsBettingVO vo) {
		return sqlSession.insert("totoSports.insertSportsBetting", vo);
	}

	@Override
	public double getSportsMatchBet(SportsVO vo) {
		return sqlSession.selectOne("totoSports.getSportsMatchBet", vo);
	}

	@Override
	public int updateSportsGrpSumBet(SportsBettingVO vo) {
		return sqlSession.update("totoSports.updateSportsGrpSumBet", vo);
	}

	@Override
	public boolean isDupleBet(SportsBettingVO vo) {
		return sqlSession.selectOne("totoSports.isDupleBet", vo);
	}

	@Override
	public List<SportsVO> listSportsTotoInnerSoccer(int familyCustNo) {
		return sqlSession.selectList("totoSports.listSportsTotoInnerSoccer", familyCustNo);
	}

	@Override
	public List<SportsVO> listBoughtSportsNo(int familyCustNo) {
		return sqlSession.selectList("totoSports.listBoughtSportsNo", familyCustNo);
	}

	@Override
	public boolean isCanSportsTotoSChedule(SportsBettingVO vo) {
		return sqlSession.selectOne("totoSports.isCanSportsTotoSChedule", vo);
	}
}
