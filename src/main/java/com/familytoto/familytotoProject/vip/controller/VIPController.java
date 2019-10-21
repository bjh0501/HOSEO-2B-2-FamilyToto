package com.familytoto.familytotoProject.vip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.vip.domain.VipVO;
import com.familytoto.familytotoProject.vip.service.VipService;

@Controller
public class VIPController {
	@Autowired
	VipService vipService;
	
	@RequestMapping(value = { "/vipVasic"})
    public String index() {
        return "/viproom/vipVasic";
    }
	
	@RequestMapping(value = { "/vipEnt"})
    public String vipEnt() {
        return "/viproom/vipEnt";
    }
	
	@RequestMapping(value = { "/vipIntro"})
    public String vipIntro() {
        return "/viproom/vipIntro";
    }
	
	@RequestMapping(value = { "/vipOpenrr"})
    public String vipOpenrr() {
        return "/viproom/vipOpenrr";
    }
	
	@RequestMapping(value = { "/vip/insertGameRoom"})
	@ResponseBody
    public int vipInsertGameRoom(@ModelAttribute VipVO vipVo,
    		HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo == null) {
			return -1;
		}
		
		vipVo.setGameGubun("RSI");
		vipVo.setRegCustNo(custVo.getCustNo());
		vipVo.setRegIp(request.getRemoteAddr());		
		
        return vipService.insertGameRoom(vipVo);
    }
	
	@RequestMapping(value = { "/vip/insertGamePlayer"})
	@ResponseBody
    public int vipInsertGamePlayer(@ModelAttribute VipVO vipVo,
    		HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		int vipGameNo) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo == null) {
			return -1;
		}
		
		vipVo.setVipGameNo(vipGameNo);
		vipVo.setCreditId(0); // 수정
		vipVo.setFamilyCustNo(custVo.getFamilyCustNo());
		vipVo.setRegCustNo(custVo.getRegCustNo());
		vipVo.setRegIp(request.getRemoteAddr());
		
        return vipService.insertGamePlayers(vipVo);
    }
}
