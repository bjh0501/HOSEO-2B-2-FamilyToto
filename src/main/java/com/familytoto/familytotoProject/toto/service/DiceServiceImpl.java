package com.familytoto.familytotoProject.toto.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.dao.DiceDao;
import com.familytoto.familytotoProject.toto.domain.DiceVO;

@Service
public class DiceServiceImpl implements DiceService {
	@Autowired
	DiceDao diceDao;
	
	@Autowired
	CommonDao commonDao;
	
	@Override
	@Transactional
	public Map<String, Object> insertDiceBet(DiceVO vo, CreditVO creVo) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("error", "0");
		creVo.setCreditValue(creVo.getCreditValue()*-1);
		
		if(creVo.getCreditValue() < 1000) {
			map.put("error", "-98");
			return map;
		}
		
		if(commonDao.isHaveCredit(creVo) == false) {
			map.put("error", "-99");
			return map;
		} else {
			creVo.setCreditValue(creVo.getCreditValue()*-1);
		}
		
		int randomDice = 2;
		int randomValue1 = (int)(Math.random()*6) +1;
		int randomValue2 = (int)(Math.random()*6) +1;
		
		vo.setDiceAmount(randomDice);
		vo.setDiceResult(randomValue1+randomValue2);
		
		if(randomValue1 == randomValue2) {
			vo.setDiceOption("D");
			vo.setDiceBet(3.95);
		} else if( (randomValue1 + randomValue2) % 2 == 0 ) {
			vo.setDiceOption("J");
			vo.setDiceBet(1.95);
		} else {
			vo.setDiceOption("H");
			vo.setDiceBet(1.95);
		}
		
		if(diceDao.insertCredit(creVo) == 1) {
			vo.setCreditId(creVo.getCreditId());
			
			if(diceDao.insertDiceBet(vo) != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				map.put("error", "-2");
			};
			
			map.put("dice1", randomValue1);
			map.put("dice2", randomValue2);
			
			if(vo.getDiceOption().equals(vo.getDiceCustBetOption())) {
				int getCredit = (int) (vo.getDiceBet()* creVo.getCreditValue())*-1 ;
				creVo.setCreditValue(getCredit);
				map.put("getCredit", getCredit);
				diceDao.insertCredit(creVo);
			} else {
				map.put("getCredit", 0);
				map.put("error", "1");
			}
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			map.put("error", "-1");
		}
		
		return map;
	}
}
