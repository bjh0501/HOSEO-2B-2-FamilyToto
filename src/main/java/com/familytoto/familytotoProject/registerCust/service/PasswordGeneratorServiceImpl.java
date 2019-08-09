package com.familytoto.familytotoProject.registerCust.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class PasswordGeneratorServiceImpl implements PasswordGeneratorService {

	@Override
	public String toEncodePassword(String sPass) {
		// 숫자가 높을수록 안전, 속도는 느림
		return BCrypt.hashpw(sPass, BCrypt.gensalt(10));
	}

	@Override
	public boolean toDecodePassword(String sPass, CustVO vo) {
		return BCrypt.checkpw(sPass, vo.getCustPassword());
	}
	
}
