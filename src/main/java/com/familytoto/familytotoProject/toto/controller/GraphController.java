package com.familytoto.familytotoProject.toto.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.familytoto.familytotoProject.charge.domain.CreditVO;
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
	
	private double timeValue = 1.00;
	private int graphNo = 0;
	private int creditValue = 0;
	
	@RequestMapping("/toto/graph")
	public String goGraph(HttpSession session, Model model) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
		model.addAttribute("creditInfo", commonService.getCustCredit(cVo));
		
		return "/toto/graph";
	}
	
	@RequestMapping("/toto/graph/start")
	@ResponseBody
	@Transactional
	public int startGraph(HttpSession session,
			HttpServletRequest request,
			@Valid @ModelAttribute CreditVO creVo) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		if(cVo == null) {
			return -1;
		}
		
		// 기본 초기값들
		timeValue = 1.00;
		graphNo = 0;
		creditValue = 0;
		
		creVo.setCreditState("GBP");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
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
		
		double randomMaxValue = superRandom();
		randomMaxValue = Double.parseDouble(String.format("%.2f", randomMaxValue));
		
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		vo.setGraphMax(randomMaxValue);
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		if(graphService.insertGraphGame(vo) == 1) {
			graphNo = vo.getGraphNo();
			return 0;
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
	}
	
	@RequestMapping("/toto/graph/process")
	@ResponseBody
	public double processGraph(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -2;
		}
		
		GraphVO vo = new GraphVO();
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setGraphNo(graphNo);
		vo.setGraphMax(timeValue);
		
		// 잘못된 데이터면 패배로 기록
		if(graphService.isProcessGame(vo) == true) {
			timeValue += 0.01;
		} else {
			return graphService.updateRecordLose(vo) == 1 ? 0 : -1;
		}
		
		return timeValue;
	}
	
	@RequestMapping("/toto/graph/stop")
	@ResponseBody
	@Transactional
	public int processStopGraph(HttpSession session,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -1;
		}
		
		GraphVO vo = new GraphVO();
		vo.setGraphMax(timeValue);
		vo.setGraphNo(graphNo);
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setGraphStopBet(timeValue);
		
		if(graphService.isCorrectStopGraph(vo)==false) {
			return -2;
		}
		
		CreditVO creVo = new CreditVO();
		creVo.setCreditValue((int) (timeValue*creditValue));
		creVo.setCreditState("DBG");
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		if(graphService.updateStopGraph(vo) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
		
		return graphService.insertGetBet(creVo) == 1 ? creVo.getCreditValue() : -1;
	}
	
	public double superRandom() {
		double value = (double) (Math.random() * 10)+1;
		
		return value;
	}
	
}
