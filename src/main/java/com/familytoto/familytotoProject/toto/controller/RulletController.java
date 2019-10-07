package com.familytoto.familytotoProject.toto.controller;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.RulletVO;
import com.familytoto.familytotoProject.toto.service.CommonService;
import com.familytoto.familytotoProject.toto.service.RulletService;

@Controller
public class RulletController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	RulletService rulletService; 
	
	@RequestMapping("/toto/rullet")
	public ModelAndView goRullet(HttpSession session,
			HttpServletResponse response,
			ModelAndView mv) {
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
				
		mv.addObject("creditInfo", commonService.getCustCredit(cVo));
		mv.addObject("accumRulletCredit", rulletService.getAccumCredit());
		mv.setViewName("/toto/rullet");
		
		rulletService.updateInitialRullet(cVo.getFamilyCustNo());
		
		
		return mv;
	}
	
	@RequestMapping("/toto/rullet/start")
	@ResponseBody
	public int rulletStart(HttpSession session,
			@Valid @ModelAttribute RulletVO vo,
			@Valid @ModelAttribute CreditVO creVo,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(creVo.getCreditValue() < 1000 || creVo.getCreditValue() > 100000) {
			return -98;
		}
			
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRulletResult("3");
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setRegCustNo(cVo.getRegCustNo());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		return rulletService.insertRulletBet(vo, creVo, request);
	}
	
	@RequestMapping("/toto/rullet/end")
	@ResponseBody
	public int rulletEnd(HttpSession session,
			HttpServletRequest request,
			String sResult[]) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		RulletVO rVo = new RulletVO();		
		
		rVo.setRulletCustResult(sResult[0] + sResult[1] + sResult[2]);
		rVo.setRulletBet(betOperate(sResult));
		rVo.setFamilyCustNo(cVo.getFamilyCustNo());
		rVo.setRegCustNo(cVo.getCustNo());
		
		return rulletService.updateRulletEnd(rVo, request);
	}
	
	// 배팅연산
	public double betOperate(String[] sNumbers) {
		int[] nums = Arrays.asList(sNumbers).stream().mapToInt(Integer::parseInt).toArray();
		Arrays.sort(nums);
		
		if((nums[0]==2 && nums[1] == 4 && nums[2] == 6) 
				|| (nums[0]==2 && nums[1] == 4 && nums[2] == 6)
				|| (nums[0]==4 && nums[1] == 6 && nums[2] == 8)
				|| (nums[0]==1 && nums[1] == 3 && nums[2] == 5)
				|| (nums[0]==3 && nums[1] == 5 && nums[2] == 7)
				|| (nums[0]==5 && nums[1] == 7 && nums[2] == 9)
				|| (nums[0]==3 && nums[1] == 6 && nums[2] == 9)) {
			return 1.49;
		}
		
		// 전부 같은숫자인 경우
		if(nums[0]==nums[1] && nums[0] == nums[2]) {
			if(sNumbers[0].equals("7")) {
				return -777;
			} else {
				return 4.95;
			}
		}

		if(nums[0] == nums[1] ||
				nums[0] == nums[2] ||
				nums[1] == nums[2]) {
			return 2.95;
		}
		
		if(sNumbers[0].equals("7") ||
				sNumbers[1].equals("7") ||
				sNumbers[2].equals("7")) {
			return 1.95;
		}
		
		return 0;
	}
}