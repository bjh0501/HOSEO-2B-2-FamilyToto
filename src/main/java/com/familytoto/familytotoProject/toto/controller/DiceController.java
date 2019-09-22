package com.familytoto.familytotoProject.toto.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.DiceVO;
import com.familytoto.familytotoProject.toto.service.CommonService;
import com.familytoto.familytotoProject.toto.service.DiceService;
import com.google.gson.Gson;

@Controller
public class DiceController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	DiceService diceService;
	
	@RequestMapping("/toto/dice")
	public String goDice(HttpSession session,Model model) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
		model.addAttribute("creditInfo", commonService.getCustCredit(cVo));
		
		return "/toto/dice";
	}
	
	@RequestMapping("/toto/dice/bet")
	@ResponseBody
	public String bet(@ModelAttribute @Valid DiceVO vo,
			CreditVO creVo,
			HttpSession session,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		creVo.setCreditValue(creVo.getCreditValue()*-1);
		creVo.setCreditState("GBP");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		Gson gson = new Gson();
		
		return gson.toJson(diceService.insertDiceBet(vo, creVo));
	}
}
