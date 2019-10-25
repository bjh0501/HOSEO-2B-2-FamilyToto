package com.familytoto.familytotoProject.vip.service;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.vip.domain.VipVO;

public interface VipService {
	// VIP 게임방생성
	int insertGameRoom(VipVO vo);
	
	// VIP 게임 참여자 인서트
	int insertGamePlayers(VipVO vo);
	
	// VIP 게임참가 크레딧 걷기
	int insertVipBet(CreditVO vo);
	
	// VIP 게임참가 크레딧 걷기
	boolean isVipCreditValue(CreditVO vo);
	
	// VIP 게임 참여자수
	int getVipGamePlayersCnt(int vipGameNo);
	
	// VIP 게임 승리 크레딧받기
	int insertVipWinnerCredit(CreditVO vo);
	
	// VIP 게임방못들어오게 막기
	int updateCloseGameRoom(int vipGameNo);
	
	// VIP인지 체크
	boolean isFamilyCustVip(int familyCustNo);
}
