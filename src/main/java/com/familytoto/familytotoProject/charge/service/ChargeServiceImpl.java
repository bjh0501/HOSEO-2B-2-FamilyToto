package com.familytoto.familytotoProject.charge.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.findIdPw.service.EmailService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class ChargeServiceImpl implements ChargeService {
	@Autowired
	ChargeDao chargeDao; 
	
	@Autowired
	EmailService emailService;
	
	@Override
	public Map<String, Object> getCreditInfo(CustVO vo) {
		return chargeDao.getCreditInfo(vo);
	}

	@Override
	public int doCharge(CreditVO vo) {
		// 오늘 충전한거 갯수 예외처리하기
		Map<String, Object> map = chargeDao.todayCharge(vo);
		
		if(Integer.parseInt(map.get("todayChargeCnt").toString()) >= 5) {
			return -98;
		} else if(Integer.parseInt(map.get("remainCredit").toString()) >= 5000) {
			return -97;
		} else {
			return chargeDao.doCharge(vo);
		}
	}

	@Override
	public void sendHistoryEmail(String to, int credit) {
		String sTitle = "[원스포츠] 충전내역";
		String sContents = "";
		sContents += "원스포츠입니다.\n";
		sContents += "충전한 크레딧 : " + credit + "\n";
		
		
		emailService.sendEmail(to, sTitle, sContents);
	}
	
}
