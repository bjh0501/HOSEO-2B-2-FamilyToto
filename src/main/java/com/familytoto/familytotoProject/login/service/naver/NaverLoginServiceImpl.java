package com.familytoto.familytotoProject.login.service.naver;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.familytoto.familytotoProject.login.dao.naver.NaverDao;

@Service
public class NaverLoginServiceImpl implements NaverLoginService {
	@Autowired
	NaverDao naverDao;
	
	private NaverLoginVO naverLoginVO;

	@Autowired
	private void setNaverLoginVO(NaverLoginVO naverLoginVO) {
		this.naverLoginVO = naverLoginVO;
	}

	@Override
	public String naverAuthLink(Model model, HttpSession session) {
		return naverLoginVO.getAuthorizationUrl(session);
	}

	@Override
	public int insertNaverId(Map<String, Object> map) {
		Map<String, Object> checkMap = naverDao.checkNaver(map);
		
		// 소셜custId값 넘김
		if(checkMap == null) {
			return naverDao.insertNaver(map);
		} else {
			return Integer.parseInt(checkMap.get("scCustNo").toString());
		}
	}

	@Override
	public String naverAuthChangeLink(Model model, HttpSession session) {
		return naverLoginVO.getAuthorizationChangeUrl(session);
	}	
}
