package com.familytoto.familytotoProject.dashboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CreditVO> listRecentCredit(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRecentProfitCredit", familyCustNo);
	}

	@Override
	public int getTotalCredit(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalCredit", familyCustNo);
	}

	@Override
	public int getTotalMileage(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalMileage", familyCustNo);
	}

	@Override
	public int getTotalExp(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalExp", familyCustNo);
	}

	@Override
	public List<MileageVO> listRecentMileage(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRecentProfitMileage", familyCustNo);
	}	
}
