package com.familytoto.familytotoProject.toto.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView goSadari(HttpSession session,
			ModelAndView mv,
			HttpServletResponse response) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('연동이 안된 소셜아이디는 토토를 할 수 없습니다. "
						+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
				out.flush();
				
				mv.setViewName("/");
				
				return mv;
			} catch(Exception e) {}
		}
		
		ladderService.initFirstGubun(); // 사다리 게임준비 하기(꼭있어야함)
		
		mv.addObject("creditInfo", commonService.getCustCredit(cVo));
		mv.setViewName("/toto/ladder");
		
		return mv;
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
		
		if(creVo.getCreditValue() < 1000 || creVo.getCreditValue() > 100000) {
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
		} else if (winGubun == -99) {
			return -99;
		} else {
			return -2;
		}
	}
}