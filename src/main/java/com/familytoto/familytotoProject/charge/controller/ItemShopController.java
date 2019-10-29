package com.familytoto.familytotoProject.charge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.charge.service.ItemShopService;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class ItemShopController {
	@Autowired
	ItemShopService itemShopService;
	
	@RequestMapping("/itemShop")
    public String itemShop() {
        return "/shop/itemShop";
    }
	
	@RequestMapping("/itemShop/buyNickname")
	@ResponseBody
	@Transactional
    public int buyNickname(@ModelAttribute @Valid CreditVO vo,
    		HttpSession session, HttpServletRequest request) {
		int needCredit = 1000;
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {  
			return -99;			
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		// 돈없는경우
		vo.setCreditValue(needCredit);
		if(isCheckCredit(vo)== false) {
			return -98;
		} else {
			vo.setCreditValue(needCredit*-1);
			vo.setCreditState("CHN");
			vo.setRegCustNo(cVo.getCustNo());
			vo.setRegIp(request.getRemoteAddr());
			itemShopService.insertCredit(vo);
		}
		
		String nickname = request.getParameter("nickname");
		
		if(nickname.length() > 8 || nickname.length() < 2) {
			throw new RuntimeException("닉은 2~8까지만가능");
		}
		
		// 존재 닉네임
		if(itemShopService.isCheckNickname(nickname) == true) {
			return -97;
		} else {
			cVo.setFamilyCustNick(nickname);
		}
		
		if(itemShopService.updateNickname(cVo) == 1) {
			return 0;		
		} else {
			return -1;
		}
    }

	@RequestMapping("/itemShop/buyVIP")
	@ResponseBody
	@Transactional
    public int buyVipTicket(@ModelAttribute @Valid CreditVO vo,
    		HttpSession session,
    		HttpServletRequest request,
    		String dt) {
		
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {  
			return -99;			
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		
		// 이미 VIP권이 있는경우
		if(itemShopService.isVipTicket(cVo.getFamilyCustNo()) == true) {
			return -97;
		}
		
		// 돈없는경우
		int needCredit = 0;
		
		if(dt.equals("1")) {
			needCredit = 10000;
		} else if(dt.equals("7")) {
			needCredit = 50000;
		} else {
			needCredit = 200000;
		}
		
		vo.setCreditValue(needCredit);
		
		if(isCheckCredit(vo)== false) {
			return -98;
		} else {
			vo.setCreditValue(needCredit*-1);
			vo.setCreditState("VIP");
			vo.setRegCustNo(cVo.getCustNo());
			vo.setRegIp(request.getRemoteAddr());

			if(itemShopService.insertCredit(vo) != 1) {
				throw new RuntimeException("크레딧 추가 에러");
			}
			
			ExpVO expVo = new ExpVO();
			expVo.setFamilyCustNo(vo.getFamilyCustNo());
			expVo.setRegCustNo(cVo.getCustNo());
			expVo.setRegExpDt(dt);
			expVo.setRegIp(vo.getRegIp());
			
			if(itemShopService.insertVipTicket(expVo) != 1) {
				throw new RuntimeException("VIP 구입에러");
			}
			
			if(itemShopService.updateVipExp(cVo.getFamilyCustNo()) != 1) {
				throw new RuntimeException("VIP 경험치 갱신에러");
			}
			
			return 0;
		}
    }
	
    public boolean isCheckCredit(CreditVO vo) {
    	return itemShopService.checkBuyCharge(vo);
    }
}
