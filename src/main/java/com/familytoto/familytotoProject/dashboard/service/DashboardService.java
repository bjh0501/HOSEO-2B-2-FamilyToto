package com.familytoto.familytotoProject.dashboard.service;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;

public interface DashboardService {
	// 최근크레딧 리스트 가져오기 
	List<CreditVO> listRecentCredit(int familyCustNo);
	
	// 최근마일리지 리스트 가져오기
	List<MileageVO> listRecentMileage(int familyCustNo);
	
	// 기본정보 가져오기
	Map<String, ?> getDefaultInfo(int familyCustNo);
}
