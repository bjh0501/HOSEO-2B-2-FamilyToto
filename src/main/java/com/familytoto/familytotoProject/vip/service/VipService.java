package com.familytoto.familytotoProject.vip.service;

import com.familytoto.familytotoProject.vip.domain.VipVO;

public interface VipService {
	// VIP 게임방생성
	int insertGameRoom(VipVO vo);
	
	// VIP 게임 참여자 인서트
	int insertGamePlayers(VipVO vo);
}
