package com.familytoto.familytotoProject.registerCust.dao;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.domain.ZipcodeVO;

public interface RegisterCustDAO {
	public int insert(RegisterCustVO vo);
	public Map<String, Object> checkNickname(RegisterCustVO vo);
	int insertRecommend(RegisterCustVO vo);
	Map<String, Object> checkRecommend(CustVO vo);
	
	// 집주소
	List<ZipcodeVO> listSido(); 
	
	List<ZipcodeVO> listGugun(String sido);
	
	List<ZipcodeVO> listDong(ZipcodeVO vo);
	
	List<ZipcodeVO> listZip(ZipcodeVO vo);
	
	// 아이디체크
	Map<String, Object> getUsedId(CustVO vo);
}
