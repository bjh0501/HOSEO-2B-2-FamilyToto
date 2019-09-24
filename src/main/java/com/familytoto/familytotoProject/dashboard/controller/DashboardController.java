package com.familytoto.familytotoProject.dashboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.dashboard.service.DashboardService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

@Controller
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		model.addAttribute("list", dashboardService.getDefaultInfo(cVo.getFamilyCustNo()));
		
		return "/loginInfo/dashboard";
	}
	
	@RequestMapping("/dashboard/getCredit")
	@ResponseBody
	public String getCredit(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRecentCredit(cVo.getFamilyCustNo()));
		
		return json;
	}
	
	@RequestMapping("/dashboard/getMileage")
	@ResponseBody
	public String getMileage(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRecentMileage(cVo.getFamilyCustNo()));
		
		return json;
	}
}
