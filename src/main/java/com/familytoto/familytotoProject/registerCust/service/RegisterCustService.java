package com.familytoto.familytotoProject.registerCust.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.DeliveryVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.domain.ZipcodeVO;

public interface RegisterCustService {
	int insertRegisterCust(RegisterCustVO vo, HttpServletRequest request);
	Map<String, Object> checkNickname(RegisterCustVO vo);
	int insertRecommend(RegisterCustVO vo);
	Map<String, Object> checkRecommend(CustVO vo);
	
	// 집주소
	List<ZipcodeVO> listSido(); 
	
	List<ZipcodeVO> listGugun(String sido);
	
	List<ZipcodeVO> listDong(ZipcodeVO vo);
	
	List<ZipcodeVO> listZip(ZipcodeVO vo);
	
	// 아이디체크
	Map<String, Object> getUsedId(CustVO vo);
	
	// 택배사 불러오기
	List<DeliveryVO> listDelivery();
}