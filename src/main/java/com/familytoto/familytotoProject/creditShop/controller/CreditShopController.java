package com.familytoto.familytotoProject.creditShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.creditShop.service.CreditShopService;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;

@Controller
public class CreditShopController {
	@Autowired
	CreditShopService creditShopService;
	
	@Autowired
	ProductBuyService productBuyService;
	
	@RequestMapping("/creditShop")
    public String creditShop(Model model) {
		return "/shop/creditShop/creditShop";
    }
	
	@RequestMapping("/showProduct/{productNo}")
    public String showProduct(ProductVO vo, Model model, @PathVariable("productNo") int nProductNo) {
		ProductVO pVo= new ProductVO();
		vo.setProductNo(nProductNo);
		pVo = creditShopService.getShowProduct(vo);
		model.addAttribute("product", pVo);
		
		
		return "/shop/creditShop/showProduct";
    }
}
