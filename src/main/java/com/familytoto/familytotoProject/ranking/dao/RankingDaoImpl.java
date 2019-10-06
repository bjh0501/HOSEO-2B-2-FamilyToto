package com.familytoto.familytotoProject.ranking.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.ranking.domain.RankingVO;

@Repository
public class RankingDaoImpl implements RankingDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<RankingVO> listExpRanking() {
		return sqlSession.selectList("ranking.listExpRanking");
	}

	@Override
	public List<RankingVO> listGameRanking(String gameName) {
		return sqlSession.selectList("ranking.listGameRanking", gameName);
	}
}
