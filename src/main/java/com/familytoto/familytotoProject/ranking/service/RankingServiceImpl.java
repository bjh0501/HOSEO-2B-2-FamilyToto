package com.familytoto.familytotoProject.ranking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.ranking.dao.RankingDao;
import com.familytoto.familytotoProject.ranking.domain.RankingVO;

@Service
public class RankingServiceImpl implements RankingService {
	@Autowired
	RankingDao rankingDao;
	
	@Override
	public List<RankingVO> listExpRanking() {
		return rankingDao.listExpRanking();
	}

	@Override
	public List<RankingVO> listGameRanking(String gameName) {
		return rankingDao.listGameRanking(gameName);
	}
}
