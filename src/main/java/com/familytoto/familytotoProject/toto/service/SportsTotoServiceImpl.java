package com.familytoto.familytotoProject.toto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.dao.SportsTotoDao;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

@Service
public class SportsTotoServiceImpl implements SportsTotoService{
	@Autowired
	SportsTotoDao sportsTotoDao;
	
	@Override
	
	public int insertBuyToto(SportsBettingVO vo, CreditVO creVo, SportsVO sVo) {
		
//		creditId	// 크레딧id get
//		betGroupId // 배팅그룹 get
//
//
//		insertSportsBetting // 배팅insert
//		
//		
//		
//		sportsTotoDao.insertSportsBetting(vo);
//		
		return 0;
	}

	@Override
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
	public List<SportsVO> listSportsTotoInnerSoccer() {
		return sportsTotoDao.listSportsTotoInnerSoccer();
	}
}
