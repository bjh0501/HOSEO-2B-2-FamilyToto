package com.familytoto.familytotoProject.vip.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.vip.domain.VipVO;

@Repository
public class VipDaoImpl implements VipDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertGameRoom(VipVO vo) {
		return sqlSession.insert("vip.insertGameRoom", vo);
	}

	@Override
	public int insertGamePlayers(VipVO vo) {
		return sqlSession.insert("vip.insertGamePlayers", vo);
	}

}
