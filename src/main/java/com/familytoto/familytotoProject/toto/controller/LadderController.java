package com.familytoto.familytotoProject.toto.controller;

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
import com.familytoto.familytotoProject.toto.domain.LadderVO;
import com.familytoto.familytotoProject.toto.service.CommonService;
import com.familytoto.familytotoProject.toto.service.LadderService;

@Controller
public class LadderController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	LadderService ladderService;
	
	@RequestMapping("/toto/ladder")
	public String goSadari(HttpSession session, Model model) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
		ladderService.initFirstGubun(); // 사다리 게임준비 하기(꼭있어야함)
		
		model.addAttribute("creditInfo", commonService.getCustCredit(cVo));
		return "/toto/ladder";
	}
	
	@RequestMapping("/toto/ladder/bet")
	@ResponseBody
	public double betSadari(HttpSession session,
			@Valid @ModelAttribute LadderVO vo,
			@Valid @ModelAttribute CreditVO creVo,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		if(cVo == null) {
			return -1;	
		}
		
		if(creVo.getCreditValue() < 1000) {
			return -2;
		}

		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		double random = (double)(Math.random() * 2 +1);
        String bet = String.format("%.2f" , random);
        double betValue = Double.parseDouble(bet);
        
        vo.setLadderBet(betValue);
		
        creVo.setCreditState("LBP");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		creVo.setCreditValue(creVo.getCreditValue()*-1);
		
		int winGubun = ladderService.insertLadderBet(vo, creVo, cVo, request);
        
		if(winGubun == 1) {
			return vo.getLadderBet();
		} else if(winGubun == 2) {
			return 0; 
		} else {
			return -2;
		}
	}
}