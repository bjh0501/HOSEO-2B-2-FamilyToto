package com.familytoto.familytotoProject.toto.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.dao.LadderDao;
import com.familytoto.familytotoProject.toto.domain.LadderVO;

@Service
public class LadderServiceImpl implements LadderService {
	@Autowired
	LadderDao ladderDao;
	
	@Autowired
	ExpService expService;
	
	@Autowired
	CommonDao commonDao;
	
	private static int resultArr[] = {0,0,0,0,0}; 
	private static boolean isFirstGubun = false; 
	private static int userCredit = 0; // 크레딧 박제
	/*
	 * 
	 * 크레딧 확인
	 * 시작 경험치얻기
	 * 크레딧 소모 insert
	 * 배팅 insert
	 * 
	 * 만약 이기면
	 * 		크레딧 얻기 insert
	 * 		win 경험치 얻기 insert
	 * 		
	 */
	@Override
	@Transactional
	public int insertLadderBet(LadderVO vo,
			CreditVO creVo,
			CustVO cVo,
			HttpServletRequest request) {
		int nCheckCredit = creVo.getCreditValue() * -1; 
		// 크레딧확인
		creVo.setCreditValue(nCheckCredit);
		if(commonDao.isHaveCredit(creVo) == false) {
			return -99;
		} else {
			creVo.setCreditValue(nCheckCredit *-1);
		}
		
		// 처음일때만탐
		if(isFirstGubun == false) {
			resultArr[0] = 0;
			resultArr[1] = 0;
			resultArr[2] = 0;
			resultArr[3] = 0;
			resultArr[4] = 0;
			
			isFirstGubun = true;
			
			resultArr[superRandom()-1] = 1; // 정답 넣기
			
			userCredit = creVo.getCreditValue();
		} else {
			// 게임을 계속 진행할경우 JS에서 변경할수도있으니 
			// 초기에 넣어둔 userCredit으로 게임을 진행한다.
			creVo.setCreditValue(userCredit);
		}
		
		expService.insertExp(cVo, "LGS", 100, request);	// 시작결험치
		ladderDao.insertLadderCredit(creVo);			//- 크레딧소모 
		ladderDao.insertLadderBet(vo);					// 배팅 insert
		
		if(resultArr[vo.getLadderAnswer()-1] == 1) {		// 만약이기면
			creVo.setCreditValue(creVo.getCreditValue() * -1);
			int resultCredit = (int) (creVo.getCreditValue()*vo.getLadderBet());
			creVo.setCreditValue(resultCredit);			
			
			creVo.setCreditState("LBG");
			
			ladderDao.insertLadderCredit(creVo); // +		크레딧 얻기
			int randomExp = GlobalVariable.getWinCredit(creVo.getCreditValue());
			
			expService.insertExp(cVo, "LGW", randomExp, request); // 경험치 얻기
			
			return 1; // 이긴경우
		}
		
		
		return 2; // 진경우
	}
	
	public int superRandom() {
		int nRadom =GlobalVariable.radnomValue(1, 5); 
		return nRadom;
	}

	@Override
	// 게임준비하기
	public void initFirstGubun() {
		isFirstGubun = false;
	}
}
