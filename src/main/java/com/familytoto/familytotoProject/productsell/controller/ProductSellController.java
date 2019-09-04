package com.familytoto.familytotoProject.productsell.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.productsell.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productsell.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class ProductSellController {	
	@RequestMapping("productBuy")
    public String productBuy(HttpSession session, HttpServletResponse response) {
		
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
		
        return "loginInfo/productBuy";
    }
}
