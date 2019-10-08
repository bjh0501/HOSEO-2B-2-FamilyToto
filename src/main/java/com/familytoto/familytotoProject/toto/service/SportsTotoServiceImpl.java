package com.familytoto.familytotoProject.toto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.toto.dao.SportsTotoDao;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

@Service
public class SportsTotoServiceImpl implements SportsTotoService{
	@Autowired
	SportsTotoDao sportsTotoDao;
	
	public int insertSportsBettingGroup(SportsBettingVO vo) {
		return sportsTotoDao.insertSportsBettingGroup(vo);
	}

	@Override
	public double getSportsMatchBet(SportsVO vo) {
		return sportsTotoDao.getSportsMatchBet(vo);
	}

	@Override
	public int insertSportsBetting(SportsBettingVO vo) {
		return sportsTotoDao.insertSportsBetting(vo);
	}

	@Override
	public int updateSportsGrpSumBet(SportsBettingVO vo) {
		return sportsTotoDao.updateSportsGrpSumBet(vo);
	}

	@Override
	public boolean isDupleBet(SportsBettingVO vo) {
		return sportsTotoDao.isDupleBet(vo);
	}

	@Override
	public List<SportsVO> listSportsTotoInnerSoccer(int familyCustNo) {
		return sportsTotoDao.listSportsTotoInnerSoccer(familyCustNo);
	}

	@Override
	public List<SportsVO> listBoughtSportsNo(int familyCustNo) {
		return sportsTotoDao.listBoughtSportsNo(familyCustNo);
	}

	@Override
	public boolean isCanSportsTotoSChedule(SportsBettingVO vo) {
		return sportsTotoDao.isCanSportsTotoSChedule(vo);
	}
}
