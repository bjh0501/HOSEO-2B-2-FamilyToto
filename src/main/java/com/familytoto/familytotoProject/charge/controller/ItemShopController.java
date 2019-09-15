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
			vo.setFamilyCustNo(cVo.getFamilyCustNo());
			vo.setCreditState("CHN");
			vo.setRegCustNo(cVo.getRegCustNo());
			vo.setRegIp(request.getRemoteAddr());
			itemShopService.insertCredit(vo);
		}
		
		String nickname = request.getParameter("nickname");
		
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
	
    public boolean isCheckCredit(CreditVO vo) {
    	return itemShopService.checkBuyCharge(vo);
    }
}
