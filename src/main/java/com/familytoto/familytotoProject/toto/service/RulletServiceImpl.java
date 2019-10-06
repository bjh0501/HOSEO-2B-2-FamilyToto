package com.familytoto.familytotoProject.toto.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.dao.RulletDao;
import com.familytoto.familytotoProject.toto.domain.RulletStorageVO;
import com.familytoto.familytotoProject.toto.domain.RulletVO;

@Service
public class RulletServiceImpl implements RulletService {
	@Autowired
	RulletDao rulletDao;
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	ChargeDao chargeDao;
	
	@Autowired
	ExpService expService;
	
	private static int globalRuletNo = 0;
	private static int globalCredit = 0;
	
	
	@Override
	/*
	 * 
	 * 크레딧 확인
	 * 크레딧 소모
	 * 경험치 등록
	 * 경기 등록(W)
	 * 룰렛 돈 축적
	 * 
	 */
	@Transactional
	public int insertRulletBet(RulletVO vo,
			CreditVO creVo,
			HttpServletRequest request) {
		globalRuletNo = 0;
		globalCredit = creVo.getCreditValue();
		
		if(commonDao.isHaveCredit(creVo) == false) {
			return -99;
		}
		
		creVo.setCreditValue(globalCredit*-1);
		creVo.setCreditState("RBP");
		
		if(chargeDao.doCharge(creVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -1;
		}
		
		CustVO cVo = new CustVO();
		cVo.setFamilyCustNo(vo.getFamilyCustNo());
		cVo.setCustNo(vo.getRegCustNo());
		
		if(expService.insertExp(cVo, "RGS", 100, request) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}
		
		if(rulletDao.insertRulletBet(vo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		} else {
			globalRuletNo=vo.getRulletNo();
			
			RulletStorageVO rsVo = new RulletStorageVO();
			rsVo.setCreditId(creVo.getCreditId());
			rsVo.setRulletNo(vo.getRulletNo());
			rsVo.setFamilyCustNo(vo.getFamilyCustNo());
			rsVo.setRegCustNo(vo.getRegCustNo());
			rsVo.setRegIp(vo.getRegIp());
			
			if(rulletDao.insertRulletStorage(rsVo) != 1) {
				throw new RuntimeException("룰렛에 축적 실패");
			}
		}

		return 1;
	}

	/*
	 * 
	 * 결과 update
	 * 결과 : 돈 +
	 * 결과 : 돈이 + 면 무조건 경험치얻기
	 * 
	 */
	@Transactional
	public int updateRulletEnd(RulletVO vo, HttpServletRequest request) {
		vo.setRulletNo(globalRuletNo);
		
		if(rulletDao.updateRulletEnd(vo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}
		
		CreditVO creVo = new CreditVO();
		
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setRegCustNo(vo.getRegCustNo());
		creVo.setFamilyCustNo(vo.getFamilyCustNo());
		creVo.setCreditState("RBG");
		
		int nCreditValue = 0;
		if(vo.getRulletBet() == -777) {
			// 지금까지 모인돈
			nCreditValue = (int) (rulletDao.getAccumCredit());
			
			RulletStorageVO rsVo = new RulletStorageVO();
			rsVo.setChgCustNo(vo.getRegCustNo());
			rsVo.setFamilyCustNo(vo.getFamilyCustNo());
			
			if(rulletDao.updateRulletAccumCredit(rsVo) < 1) {
				throw new RuntimeException("축적된돈 가져오기 실패");
			}
		} else {
			nCreditValue = (int) (globalCredit * vo.getRulletBet());
		}
				
		creVo.setCreditValue(nCreditValue);
		
		if(chargeDao.doCharge(creVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
		
		CustVO cVo = new CustVO();
		cVo.setFamilyCustNo(vo.getFamilyCustNo());
		cVo.setCustNo(vo.getRegCustNo());
		
		int randomExp = GlobalVariable.getWinCredit(creVo.getCreditValue());
		
		if(expService.insertExp(cVo, "RGW", randomExp, request) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -4;
		}
		
		return nCreditValue;
	}

	@Override
	public int updateInitialRullet(int familyCustNo) {
		return rulletDao.updateInitialRullet(familyCustNo);
	}

	@Override
	public int getAccumCredit() {
		return rulletDao.getAccumCredit();
	}

}
