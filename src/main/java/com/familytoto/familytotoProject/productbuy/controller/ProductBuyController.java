package com.familytoto.familytotoProject.productbuy.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class ProductBuyController {
	@Autowired
	ProductBuyService productBuyService;
	
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
	            out.println("<script>alert('연동이 안된 소셜아이디는 개인정보를 수정할 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();	            
			} catch(Exception e) {}
		}
		
		ProductBuyVO pbVo = new ProductBuyVO();
		pbVo.setProductNo(productNo);
		
		ProductVO pVo = productBuyService.getProductBuy(pbVo);
		
		if(sAmount != null) {
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
}
