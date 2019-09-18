package com.familytoto.familytotoProject.productbuy.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.creditShop.service.CreditShopService;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class ProductBuyController {
	@Autowired
	ProductBuyService productBuyService;
	
	@Autowired
	CreditShopService creditShopService;
	
	@RequestMapping("/productBuy/{productNo}")
    public ModelAndView productBuy(HttpSession session, HttpServletResponse response,
    		HttpServletRequest request, @PathVariable("productNo") int productNo) {
		String sAmount = request.getParameter("amount");
		
		ModelAndView mv = new ModelAndView();
		
		
		// 소셜 아이디
		CustVO vo = (CustVO) session.getAttribute("cust");
		
		if(vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 구입할 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();	            
			} catch(Exception e) {}
		}
		
		ProductBuyVO pbVo = new ProductBuyVO();
		pbVo.setProductNo(productNo);
		
		ProductVO pVo = productBuyService.getProductBuy(pbVo);
		
		if(sAmount != null 
				&& pVo.getProductAmount() >= Integer.parseInt(sAmount)) { // 장바구니에 담앗는데 누군가사서 없어질수도 
			pVo.setProductAmount(Integer.parseInt(sAmount));
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('정상적인 경로로 접속해주세요!');location.replace('/');</script>");
	            out.flush();	            
			} catch(Exception e) {}
		}
		
		if(pVo != null) {
			pVo.setTotalCredit(pVo.getProductCredit() * pVo.getProductAmount());
			mv.addObject("product", pVo);
			mv.setViewName("/loginInfo/productBuy");
			mv.addObject("custInfo", productBuyService.getCustInfo(vo.getFamilyCustNo()));
			
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('해당 상품이 존재하지 않습니다.');location.replace('/');</script>");
	            out.flush();	            
			} catch(Exception e) {}
		}
        
        return mv;
    }
	
	@RequestMapping("/basketBuy")
    public ModelAndView basketBuy(HttpSession session, HttpServletResponse response,
    		HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/loginInfo/productBuyByBasket");
		// 소셜 아이디
		CustVO vo = (CustVO) session.getAttribute("cust");
		
		if(vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 구입할 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();	            
			} catch(Exception e) {}
		}
				
		mv.addObject("custInfo", productBuyService.getCustInfo(vo.getFamilyCustNo()));
		mv.addObject("product", productBuyService.listProductBuy(vo.getFamilyCustNo()));
        return mv;
    }
	
	@RequestMapping("/productSell/insert")
	@ResponseBody
	@Transactional // 장바구니경우 필요
	public int productSellInsert(HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response,
			@ModelAttribute @Valid ProductBuyVO vo) {
		// 소셜 아이디

		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -99;
		}
		
		if(cVo.getFamilyCustNo() == 0) {
			return -98;
		}
		
		String sProductNo[] = request.getParameterValues("productNo");
		String sProductBuAmount[] = request.getParameterValues("produtBuyAmount");
		String sBasketNo[] = null;
		
		// 장바구니 경우
		if(request.getParameterValues("basketNo") != null) {
			sBasketNo = request.getParameterValues("basketNo");
		}
		
		for(int i = 0 ; i <sProductBuAmount.length; i++) {
			int nProductAmount = Integer.parseInt(sProductBuAmount[i]);
			int nBasketNo = 0;
			
			// 장바구니 경우
			if(request.getParameterValues("basketNo") != null) {
				nBasketNo = Integer.parseInt(sBasketNo[i]);
				
				if(nBasketNo < 1) {
					return -2;
				}
			}
			
			if(nProductAmount < 1) {
				return -1;
			}
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		int nResult = 0;
		
		String sGubun = request.getParameter("gubun");
		
		if(productBuyService.insertProductGrp(vo) == 1) {
			ProductVO pVo= new ProductVO();
			
			for(int i = 0 ; i <sProductNo.length; i++) {
				pVo.setProductNo(Integer.parseInt(sProductNo[i]));
				
				pVo = creditShopService.getShowProduct(pVo);
		
				int nProductAmount = Integer.parseInt(sProductBuAmount[i]);
				
				// 장바구니 경우
				if(sBasketNo != null && sBasketNo[i] != null) {
					int nBasketNo = Integer.parseInt(sBasketNo[i]);
					vo.setBasketNo(nBasketNo);
				}
				
				vo.setProductNo(pVo.getProductNo());
				vo.setDeliveryNo(pVo.getDeliveryNo());
				vo.setProductBuyAmount(nProductAmount);
				vo.setProductBuyCredit(pVo.getProductCredit() * nProductAmount);
				vo.setRegCustNo(cVo.getCustNo());
				
				nResult = productBuyService.insertProductDirectBuy(vo, sGubun);
				
				if(nResult != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return nResult;
				}
				
			}
			
			pVo = null;
		}
		
		return 1;  
	}
}
