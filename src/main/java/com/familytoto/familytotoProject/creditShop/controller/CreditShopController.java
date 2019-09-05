package com.familytoto.familytotoProject.creditShop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.creditShop.service.CreditShopService;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class CreditShopController {
	@Autowired
	CreditShopService creditShopService;
	
	@Autowired
	ProductBuyService productBuyService;
	
	ProductVO pVo= new ProductVO(); 
	
	@RequestMapping("/creditShop")
    public String creditShop(Model model) {
		return "/shop/creditShop/creditShop";
    }
	
	@RequestMapping("/showProduct/{productNo}")
    public String showProduct(ProductVO vo, Model model, @PathVariable("productNo") int nProductNo) {
		vo.setProductNo(nProductNo);
		pVo = creditShopService.getShowProduct(vo);
		model.addAttribute("product", pVo);
		
		
		return "/shop/creditShop/showProduct";
    }
	
	@RequestMapping("productSell/insert")
	@ResponseBody
	public int productSellInsert(@Valid @ModelAttribute ProductBuyVO vo,
			HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response) {
		// 소셜 아이디
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -99;
		}
		
		if(cVo.getFamilyCustNo() == 0) {
			return -98;
		}
		
		int nProductAmount = vo.getProductBuyAmount();
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setProductNo(pVo.getProductNo());
		vo.setDeliveryNo(pVo.getDeliveryNo());
		vo.setProductBuyCredit(pVo.getProductCredit() * nProductAmount);
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		return productBuyService.insertProductDirectBuy(vo);
	}
}
