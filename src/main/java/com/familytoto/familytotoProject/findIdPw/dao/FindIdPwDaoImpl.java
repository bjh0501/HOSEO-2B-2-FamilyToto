package com.familytoto.familytotoProject.findIdPw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Repository
public class FindIdPwDaoImpl implements FindIdPwDao{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Map<String, Object>> findId(RegisterCustVO vo) {
		return sqlSession.selectList("findCust.findId", vo);
	}

	@Override
	public Map<String, Object> findPassword(String id, String email) {
        Map<String, Object> map = new HashMap<String, Object>(); // MAP을 이용해 담기
        map.put("custId", id);
        map.put("familyCustEmail", email);
		
		return sqlSession.selectOne("findCust.findPassword", map);
	}

	@Override
	public int setPassword(String sId, String sPassword) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("custPassword", sPassword);
		map.put("custId", sId);
		
		return sqlSession.update("findCust.setPassword", map);
	}
}
