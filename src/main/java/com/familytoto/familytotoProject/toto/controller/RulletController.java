package com.familytoto.familytotoProject.toto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.service.CommonService;
import com.google.auto.value.AutoAnnotation;

@Controller
public class RulletController {
	@Autowired
	CommonService commonService;
	
	@RequestMapping("/toto/rullet")
	public String goSadari(HttpSession session, Model model) {
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
		model.addAttribute("creditInfo", commonService.getCustCredit(cVo));
		return "/toto/rullet";
	}
}