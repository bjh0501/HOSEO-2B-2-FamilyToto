package com.familytoto.familytotoProject.creditShop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.creditShop.service.CreditShopService;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

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
	
	@RequestMapping("/creditShop/list")
	@ResponseBody
    public String creditShopList(Model model) {
		Gson gson = new Gson();
		String json = gson.toJson(creditShopService.listCreditShop());
		return json;
    }
	
	
	@RequestMapping("/showProduct/{productNo}")
    public String showProduct(ProductVO vo, Model model, @PathVariable("productNo") int nProductNo) {
		ProductVO pVo= new ProductVO();
		vo.setProductNo(nProductNo);
		pVo = creditShopService.getShowProduct(vo);
		model.addAttribute("product", pVo);
		
		return "/shop/creditShop/showProduct";
    }
	
	@RequestMapping("/showProduct/comment")
	@ResponseBody
    public int insertComment(@Valid @ModelAttribute ProductCommentVO vo,
    		HttpSession session,
    		HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -97;
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		return creditShopService.insertProductComment(vo);
    }
}
