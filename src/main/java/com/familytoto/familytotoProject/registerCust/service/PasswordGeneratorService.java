package com.familytoto.familytotoProject.registerCust.service;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface PasswordGeneratorService {
	String toEncodePassword(String sPass);
	boolean toDecodePassword(String sPass, CustVO vo);
}
