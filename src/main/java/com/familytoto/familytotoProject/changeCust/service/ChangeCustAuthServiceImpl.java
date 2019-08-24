package com.familytoto.familytotoProject.changeCust.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.changeCust.dao.ChangeCustAuthDao;
import com.familytoto.familytotoProject.login.dao.SocialDao;
import com.familytoto.familytotoProject.login.domain.SocialVO;

@Service
public class ChangeCustAuthServiceImpl implements ChangeCustAuthService{
	@Autowired
	ChangeCustAuthDao changeCustAuthDao; 
	
	@Override
	public int updateAuthSocial(SocialVO vo) {
		// 추가
		
		if(changeCustAuthDao.checkSocial(vo) == null) {
			return changeCustAuthDao.insertAuthSocial(vo);
		} else { // 있는경우 업뎃 
			// 만약 다른사람이 쓰는 아이디라면
			Map<String, Object> checkMap = changeCustAuthDao.checkSocial(vo);
			
			if(checkMap.get("familyCustNo").toString().equals("0")) {
				return changeCustAuthDao.authSocial(vo);
			} else {
				return -99;
			}	
		}
	}

	@Override
	public int updateUnAuthSocial(SocialVO vo) {
		return changeCustAuthDao.unAuthSocial(vo);
	}
	
	public int insertAuthKakao(Map<String, Object> map) {
		return 0;
		
	}

}
