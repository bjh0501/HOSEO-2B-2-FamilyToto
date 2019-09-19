package com.familytoto.familytotoProject.basket.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.basket.service.BasketService;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class BasketController {
	@Autowired
	BasketService basketService;
	
	@Autowired
	ProductBuyService productBuyService;
	
//	@Autowired
//	SportsTotoSchedulerService test;
//	
//	@RequestMapping("/scheduleTest")
//	@ResponseBody
//	public String test() {
//		test.inSoccer();
//		return "test";
//	}
	
	@RequestMapping("/basket")
    public ModelAndView basket(HttpSession session, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		CustVO vo = (CustVO)session.getAttribute("cust");
		
		if(vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 장바구니를 볼 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();
	            mv.setViewName("/");
	            return mv;
			} catch(Exception e) {}
		}
		
		
		basketService.updateOriginBasket(vo.getFamilyCustNo());
		List<BasketVO> list = basketService.listBasket(vo.getFamilyCustNo());
		
		mv.addObject("basket", list);
		mv.setViewName("/loginInfo/basket");
		
        return mv;
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
			ProductBuyVO pbVo = new ProductBuyVO();
			pbVo.setProductBuyAmount(vo.getBasketAmount());
			pbVo.setProductNo(vo.getProductNo());
			if(basketService.checkProductAmount(pbVo) == false) {
				return "-97";							// 기존수량 < 장바구니수량 경우
			}
			
			vo.setFamilyCustNo(custVo.getFamilyCustNo());
			vo.setRegCustNo(custVo.getCustNo());
			vo.setRegIp(request.getRemoteAddr());
			
			int nResult = basketService.insertBasket(vo);
			
			if(nResult == 1) {
				return "0";				
			} else if(nResult == -95) {
				return "-95";
			} else {
				return "-96";
			}
		}
    }
	
	@RequestMapping("/basket/delete")
	@ResponseBody
    public int deleteBasket(HttpSession session,
    		@ModelAttribute BasketVO vo,
    		HttpServletRequest request) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
			
		if (custVo == null) {
			return -99;								// 로그인 안한경우
		} else if(custVo.getFamilyCustNo() == 0) {
			return -98;								// 패밀리회원 아닌경우
		} else {			
			vo.setChgCustNo(custVo.getCustNo());
			vo.setFamilyCustNo(custVo.getFamilyCustNo());
			
			return productBuyService.updateDeleteBasket(vo); 
		}
    }
	
	@RequestMapping("/basket/chooseBuy")
	@ResponseBody
    public int chooseBuyBasket(HttpSession session,
    		@ModelAttribute BasketVO vo,
    		HttpServletRequest request) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
			
		if (custVo == null) {
			return -99;								// 로그인 안한경우
		} else if(custVo.getFamilyCustNo() == 0) {
			return -98;								// 패밀리회원 아닌경우
		} else {			
			vo.setChgCustNo(custVo.getCustNo());
			vo.setFamilyCustNo(custVo.getFamilyCustNo());
			
			return basketService.updateChooseBuyBasket(vo); 
		}
    }
}
