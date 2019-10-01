package com.familytoto.familytotoProject.toto.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.domain.GraphVO;
import com.familytoto.familytotoProject.toto.service.CommonService;
import com.familytoto.familytotoProject.toto.service.GraphService;

@Controller
public class GraphController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	GraphService graphService;
	
	@Autowired
	ExpService expService;
	
	private int graphNo = 0;
	private int creditValue = 0;
	static private double randomMaxValue = 0;
	
	@RequestMapping("/toto/graph")
	public ModelAndView goGraph(HttpSession session,
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
		mv.setViewName("/toto/graph");
		
		return mv;
	}
	
	@RequestMapping("/toto/graph/start")
	@ResponseBody
	@Transactional
	public double startGraph(HttpSession session,
			HttpServletRequest request,
			@Valid @ModelAttribute CreditVO creVo) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		if(cVo == null) {
			return -1;
		}
		
		// 기본 초기값들
		graphNo = 0;
		creditValue = 0;
		randomMaxValue = 0;
		
		creVo.setCreditState("GBP");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		expService.insertExp(cVo, "GGS", 100, request);
		
		if(creVo.getCreditValue() < 1000) {
			return -4;
		} else {
			creVo.setCreditValue(creVo.getCreditValue()*-1);
			creditValue = creVo.getCreditValue()*-1;
		}
		
		// 유효성검사떄문에 -를 +로
		creVo.setCreditValue(creVo.getCreditValue()*-1);
		
		if(graphService.isHaveCredit(creVo) == false) {
			return -99;
		} else {
			// 돈차감때문에 +를 -로
			creVo.setCreditValue(creVo.getCreditValue()*-1);
		}
		
		graphService.updateLastGamePorcess(cVo);
		
		if(graphService.insertCredit(creVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
			
		}
		
		GraphVO vo = new GraphVO();
		
		randomMaxValue = superRandom();
		randomMaxValue = Double.parseDouble(String.format("%.2f", randomMaxValue));
		
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		vo.setGraphMax(randomMaxValue);
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		if(graphService.insertGraphGame(vo) == 1) {
			graphNo = vo.getGraphNo();
			return randomMaxValue;
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
	}
	
//	@RequestMapping("/toto/graph/process")
//	@ResponseBody
//	public double processGraph(HttpSession session) {
//		CustVO cVo = (CustVO) session.getAttribute("cust");
//		
//		if(cVo == null) {
//			return -2;
//		}
//		
//		GraphVO vo = new GraphVO();
//		
//		vo.setFamilyCustNo(cVo.getFamilyCustNo());
//		vo.setGraphNo(graphNo);
//		vo.setGraphMax(timeValue);
//		
//		// 잘못된 데이터면 패배로 기록
//		if(randomMaxValue > timeValue) {
//			timeValue += 0.01;
//		} else {
//			return graphService.updateRecordLose(vo) == 1 ? 0 : -1;
//		}
//		
//		return timeValue;
//	}
	
	@RequestMapping("/toto/graph/stop")
	@ResponseBody
	@Transactional
	public int processStopGraph(HttpSession session,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		String sTimeValue = "";
		double dTimeValue = 0;
		
		if(cVo == null) {
			return -1;
		}
		
		if(request.getParameter("value") == null) {
			return -2;
		} else {
			sTimeValue = request.getParameter("value").toString();
			dTimeValue = Double.parseDouble(sTimeValue);
		}
		
		GraphVO vo = new GraphVO();
		vo.setGraphMax(dTimeValue);
		vo.setGraphNo(graphNo);
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setGraphStopBet(dTimeValue);
		
		if(graphService.isCorrectStopGraph(vo)==false) {
			return -2;
		}
		
		CreditVO creVo = new CreditVO();
		creVo.setCreditValue((int) (dTimeValue*creditValue));
		creVo.setCreditState("DBG");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		if(graphService.updateStopGraph(vo) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
		
		int nRandomExp = GlobalVariable.getWinCredit(creVo.getCreditValue());
		
		expService.insertExp(cVo, "GGW", nRandomExp, request);
		
		return graphService.insertGetBet(creVo) == 1 ? creVo.getCreditValue() : -1;
	}
	
	public double superRandom() {
		 double randomPoint = Math.random()*1;
	        double point = Double.parseDouble(String.format("%.2f", randomPoint));
	        int randomOperate = (int) (Math.random()*100)+1;
	        //최고 12.9배
	        
	        // 0.99배 10퍼 10
	        // 1.0배~1.9배 30퍼 40
	        // 2.0배~2.9배 30퍼, 70
	        // 3.0배~5.9배 10퍼, 80
	        // 6.0배~7.9배 10퍼, 90
	        // 8.0배~9.9배 5퍼, 95
	        // 10.0배~10.9배 3퍼 98
	        // 11.1배~11.9배 1퍼 99
	        // 12.0배~12.9배 1퍼 100

	        if(randomOperate <= 10) {
	            return 0.99;
	        } else if(randomOperate <= 30) {
	            return Double.parseDouble(String.format("%.2f", 1.0 + point));
	        } else if(randomOperate <= 70) {
	            return Double.parseDouble(String.format("%.2f", 2.0 + point));
	        } else if(randomOperate <= 80) {
	            double dRandVal = GlobalVariable.radnomValue(3, 5);
	            return Double.parseDouble(String.format("%.2f", dRandVal + point));
	        } else if(randomOperate <= 90) {
	            double dRandVal = GlobalVariable.radnomValue(6, 7);
	            return Double.parseDouble(String.format("%.2f", dRandVal + point));
	        } else if(randomOperate <= 95) {
	            double dRandVal = GlobalVariable.radnomValue(8, 9);
	            return Double.parseDouble(String.format("%.2f", dRandVal + point));
	        } else if(randomOperate <= 98) {
	            return Double.parseDouble(String.format("%.2f", 10.0 + point));
	        } else if(randomOperate <= 99) {
	            return Double.parseDouble(String.format("%.2f", 11.0 + point));
	        } else {
	            return Double.parseDouble(String.format("%.2f", 12.0 + point));
	        }
	}
	
}
