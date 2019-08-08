package com.familytoto.familytotoProject.registerCust.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.service.CustService;
import com.familytoto.familytotoProject.registerCust.service.RegisterCustService;

@Controller
public class RegisterCustController {
	@Autowired
	RegisterCustService registerCustService;
	
	@Autowired
	CustService custService;
	
	@RequestMapping("/registerCust")
    public String registerCust() {
        return "loginInfo/registerCust";
    }
	
	@RequestMapping(value = "/registerCust/register", method = RequestMethod.POST)
	@ResponseBody
	public String insertRegister(@ModelAttribute RegisterCustVO rcVo, @ModelAttribute CustVO cVo, HttpServletRequest request) throws Exception {
		System.out.println(registerCustService.insertRegisterCust(rcVo, request));
		cVo.setFamilyCustNo(rcVo.getFamilyCustNo());
		custService.insertCust(cVo, request);

		return "";
	}

}
