package com.familytoto.familytotoProject.vip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
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
    public String vipOpenrr(Model model, HttpSession session) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		model.addAttribute("cust", custVo);
        return "/viproom/vipOpenrr";
    }
	
	@RequestMapping(value = { "/vip/insertRRGameRoom"})
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
	@Transactional
    public int vipInsertGamePlayer(@ModelAttribute VipVO vipVo,
    		HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		int vipGameNo,
    		int familyCustNos[]) {
		int betPlusCredit = 100000;
		int betMinusCredit = 100000*-1;
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo == null) {
			return -1;
		}
		
		for(int i = 0; i < familyCustNos.length; i++) {
			CreditVO creditVo = new CreditVO(); 
			creditVo.setFamilyCustNo(familyCustNos[i]);
			creditVo.setCreditValue(betPlusCredit);
			creditVo.setCreditState("RSI");
			creditVo.setRegCustNo(custVo.getCustNo());
			creditVo.setRegIp(request.getRemoteAddr());
			
			if(vipService.isVipCreditValue(creditVo) == false) {
				throw new RuntimeException("VIP룸 크레딧 부족");
			}
			
			creditVo.setCreditValue(betMinusCredit);			
			
			vipVo.setVipGameNo(vipGameNo);
			vipVo.setCreditId(vipService.insertVipBet(creditVo)); // 수정
			vipVo.setFamilyCustNo(familyCustNos[i]);
			vipVo.setRegCustNo(custVo.getRegCustNo());
			vipVo.setRegIp(request.getRemoteAddr());
			if(vipService.insertGamePlayers(vipVo) != 1) {
				throw new RuntimeException("VIP룸 배팅 걸기 실패");
			}
		}
		
        return 0;
    }
	
	@RequestMapping(value = { "/vip/winGamePlayer"})
	@ResponseBody
    public int vipWinGamePlayer(HttpSession session,
    		HttpServletRequest request,
    		int gameRoomNo) {
		int betCredit = 100000;
		
		int playerCnt = vipService.getVipGamePlayersCnt(gameRoomNo);
		
		CustVO custVo = (CustVO) session.getAttribute("cust");
		CreditVO creditVo = new CreditVO(); 
		creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
		creditVo.setCreditValue(playerCnt*betCredit);
		creditVo.setCreditState("RSW");
		creditVo.setRegCustNo(custVo.getCustNo());
		creditVo.setRegIp(request.getRemoteAddr());
		
		if(vipService.insertVipWinnerCredit(creditVo) != 1) {
			throw new RuntimeException("승리 크레딧받기 에러");
		}
		
		return playerCnt*betCredit;
	}
    		
}
