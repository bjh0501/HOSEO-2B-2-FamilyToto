package com.familytoto.familytotoProject.dashboard.dao;

import java.util.List;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;

public interface DashboardDao {
	// 최근크레딧 리스트 가져오기
	List<CreditVO> listRecentCredit(int familyCustNo);
	
	// 최근마일리지 리스트 가져오기
	List<MileageVO> listRecentMileage(int familyCustNo);
	
	// 크레딧 가져오기
	int getTotalCredit(int familyCustNo);
	
	// 마일리지 가져오기
	int getTotalMileage(int familyCustNo);
	
	// 경험치 가져오기
	int getTotalExp(int familyCustNo);
}
