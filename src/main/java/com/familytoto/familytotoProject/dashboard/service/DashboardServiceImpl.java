package com.familytoto.familytotoProject.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.dashboard.dao.DashboardDao;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	DashboardDao dashboardDao;
	
	@Override
	public List<CreditVO> listRecentCredit(int familyCustNo) {
		return dashboardDao.listRecentCredit(familyCustNo);
	}

	@Override
	public Map<String, ?> getDefaultInfo(int familyCustNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		int credit = dashboardDao.getTotalCredit(familyCustNo);
		int mileage = dashboardDao.getTotalMileage(familyCustNo);
		int exp = dashboardDao.getTotalExp(familyCustNo);
		
		map.put("credit", credit);
		map.put("mileage", mileage);
		map.put("exp", exp);
		
		return map;
	}

	@Override
	public List<MileageVO> listRecentMileage(int familyCustNo) {
		return dashboardDao.listRecentMileage(familyCustNo);
	}

}
