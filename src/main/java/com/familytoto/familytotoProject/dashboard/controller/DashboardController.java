package com.familytoto.familytotoProject.dashboard.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.dashboard.service.DashboardService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

@Controller
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpSession session,
			ModelAndView mv,
			HttpServletResponse response) {
		CustVO vo = (CustVO) session.getAttribute("cust");

		if(vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 대시보드를 볼 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();
	            mv.setViewName("/");
	            return mv;
			} catch(Exception e) {}
		}
		
		mv.addObject("list", dashboardService.getDefaultInfo(vo.getFamilyCustNo()));
		mv.setViewName("/loginInfo/dashboard");
		
		return mv;
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
