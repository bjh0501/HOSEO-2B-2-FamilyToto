package com.familytoto.familytotoProject.registerCust.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.service.CaptchaService;
import com.familytoto.familytotoProject.registerCust.service.CustService;
import com.familytoto.familytotoProject.registerCust.service.RegisterCustService;

@Controller
public class RegisterCustController {
	@Autowired
	RegisterCustService registerCustService;
	
	@Autowired
	CustService custService;
	
	@Autowired
	CaptchaService captchaService;
	
	@RequestMapping("/registerCust")
    public String registerCust(HttpServletRequest request) {
		System.out.println(request.getContextPath() );
        return "loginInfo/registerCust";
    }
	
	@RequestMapping(value = "/registerCust/register", method = RequestMethod.POST)
	@ResponseBody
	public int insertRegister(@ModelAttribute RegisterCustVO rcVo, @ModelAttribute CustVO cVo, 
			HttpServletRequest request, HttpSession session) throws Exception {
		int nCaptchaResult = captchaService.isRight(session, request);
		
		Map<String, Object> custDupleId = custService.checkId(cVo);
		Map<String, Object> custDupleNickname = registerCustService.checkNickname(rcVo);
		
		int nResult=0;
		
		if(nCaptchaResult == 0) { // 틀린캡챠
			nResult = -99;
		} else if(custDupleId != null) { // 중복 아이디
			nResult = -98;
		} else if(custDupleNickname != null) { // 중복 닉네임
			nResult = -97;
		} else if(cVo.getCustPassword().length() < 4 || cVo.getCustPassword().length() > 20) {
			nResult = -96;
		} else {
			// 트랜잭션 걸어야함
			registerCustService.insertRegisterCust(rcVo, request);
			cVo.setFamilyCustNo(rcVo.getFamilyCustNo());
			
			custService.insertCust(cVo, request);
			// 트랜잭션 걸어야함
		}
		
		return nResult;
	}
}