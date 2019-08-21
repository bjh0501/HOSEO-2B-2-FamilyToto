package com.familytoto.familytotoProject.login.service.social;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.familytoto.familytotoProject.login.dao.SocialDao;
import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.login.service.naver.NaverLoginVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class SocialLoginServiceImpl implements SocalLoginService {
	@Autowired
	SocialDao socialDao;
	
	private NaverLoginVO naverLoginVO;
	
	@Autowired
	private void setNaverLoginVO(NaverLoginVO naverLoginVO) {
		this.naverLoginVO = naverLoginVO;
	}

	@Override
	public String socialAuthLink(Model model, HttpSession session) {
		return naverLoginVO.getAuthorizationUrl(session);
	}

	@Override
	public int insertSocialId(SocialVO vo) {
		Map<String, Object> checkMap = socialDao.checkSocial(vo);
		
		// 소셜custId값 넘김
		if(checkMap == null) {
			if(vo.getScCustGubun() != null && !vo.getScCustGubun().equals("")) {
				return socialDao.insertSocial(vo);
			} else {
				return -99;
			}
		} else {
			return Integer.parseInt(checkMap.get("scCustNo").toString());
		}
	}

	@Override
	public String socialAuthChangeLink(Model model, HttpSession session) {
		return naverLoginVO.getAuthorizationChangeUrl(session);
	}	
	
	public CustVO getSocialFamilyNo(SocialVO vo) {
		CustVO cVo = socialDao.getSocialFamilyNo(vo);
		
		if(cVo == null) {
			return socialDao.getsocialLoginNoFamilyNo(vo);
		} else {
			return cVo;
		}
	}
}
