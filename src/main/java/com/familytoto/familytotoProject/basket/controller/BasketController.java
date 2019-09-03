package com.familytoto.familytotoProject.basket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.basket.service.BasketService;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class BasketController {
	@Autowired
	BasketService basketService;
	
	@RequestMapping("/basket")
    public String basket() {
        return "/loginInfo/basket";
    }
	
	@RequestMapping("/basket/insert")
	@ResponseBody
    public String insertBasket(HttpSession session,
    		@Valid @ModelAttribute BasketVO vo,
    		HttpServletRequest request) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
				
		if (custVo == null) {
			return "-99";								// 로그인 안한경우
		} else if(custVo.getFamilyCustNo() == 0) {
			return "-98";								// 패밀리회원 아닌경우
		} else {
			ProductVO pVo = new ProductVO();
			pVo = basketService.checkProductAmount(vo);
			int nAmount = pVo.getProductAmount();
			
			if(nAmount < vo.getBasketAmount()) {
				return "-97";							// 기존수량 < 장바구니수량 경우
			}
			
			vo.setFamilyCustNo(custVo.getFamilyCustNo());
			vo.setRegCustNo(custVo.getCustNo());
			vo.setRegIp(request.getRemoteAddr());
			
			if(basketService.insertBasket(vo) == 1) {
				return "0";				
			} else {
				return "-96";
			}
		}
    }
}
