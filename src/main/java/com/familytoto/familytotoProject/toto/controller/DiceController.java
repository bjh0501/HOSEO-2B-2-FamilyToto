package com.familytoto.familytotoProject.toto.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.exp.service.ExpService;
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
	ExpService expService;
	
	@Autowired
	DiceService diceService;
	
	@RequestMapping("/toto/dice")
	public ModelAndView goDice(HttpSession session,
			ModelAndView mv,
			HttpServletResponse response) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
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
		
		mv.addObject("creditInfo", commonService.getCustCredit(cVo));
		mv.setViewName("/toto/dice");
		
		return mv;
	}
	
	@RequestMapping("/toto/dice/bet")
	@ResponseBody
	@Transactional
	public String bet(@ModelAttribute @Valid DiceVO vo,
			CreditVO creVo,
			HttpSession session,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		creVo.setCreditValue(creVo.getCreditValue()*-1);
		creVo.setCreditState("DBP");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		expService.insertExp(cVo, "DGS", 100, request);
		
		Gson gson = new Gson();
		
		return gson.toJson(diceService.insertDiceBet(vo, creVo, request, session));
	}
}
