package com.familytoto.familytotoProject.changeCust.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.changeCust.dao.ChangeCustAuthDao;
import com.familytoto.familytotoProject.login.dao.naver.NaverDao;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class ChangeCustAuthServiceImpl implements ChangeCustAuthService{
	@Autowired
	ChangeCustAuthDao changeCustAuthDao; 
	
	@Autowired
	NaverDao naverDao;
	
	@Override
	public int updateAuthNaver(Map<String, Object> map) {
		// 추가
		if(changeCustAuthDao.checkNaver(map) == null) {
			return naverDao.insertNaver(map);
		} else { // 있는경우 업뎃 
			// 만약 다른사람이 쓰는 아이디라면
			Map<String, Object> checkMap = changeCustAuthDao.checkNaver(map);
			if(checkMap.get("familyCustNo").toString().equals("0")) {
				return changeCustAuthDao.authNaver(map);
			} else {
				return -99;
			}	
		}
	}

	@Override
	public int updateUnAuthNaver(CustVO vo) {
		return changeCustAuthDao.unAuthNaver(vo);
	}

	@Override
	public Map<String, Object> getOtherAccountAuthNaver(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
