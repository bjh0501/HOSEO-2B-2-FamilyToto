package com.familytoto.familytotoProject.vip.model;

import com.familytoto.familytotoProject.vip.domain.VipVO;

public interface VipDao {
	// VIP 게임방생성
	int insertGameRoom(VipVO vo);
	
	// VIP 게임 참여자 인서트
	int insertGamePlayers(VipVO vo);
}
