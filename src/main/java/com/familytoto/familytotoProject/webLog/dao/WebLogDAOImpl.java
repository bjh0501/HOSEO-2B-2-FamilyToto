package com.familytoto.familytotoProject.webLog.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.webLog.domain.WebLogVO;

@Repository
public class WebLogDAOImpl implements WebLogDAO{
	@Inject
	SqlSession SqlSession;
	
	@Override
	public void insert(WebLogVO vo) {
		SqlSession.insert("webLog.insertWebLog", vo);
	}
}
