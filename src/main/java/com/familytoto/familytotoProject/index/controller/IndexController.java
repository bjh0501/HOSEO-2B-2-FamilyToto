package com.familytoto.familytotoProject.index.controller;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.webLog.domain.WebLogVO;
import com.familytoto.familytotoProject.webLog.service.WebLogService;

@Controller
public class IndexController {
	@Inject
	WebLogService webLogService;
	
	@RequestMapping(value = { "index", "/" })
    public String index() {
        return "index";
    }
	
	@RequestMapping("itemShop")
    public String itemShop() {
        return "shop/itemShop";
    }
	
	@RequestMapping("editor")
    public String editor() {
        return "border/editor/editor";
    }
	
	@RequestMapping("qna")
    public String qna() {
        return "border/qna";
    }
	
	@RequestMapping("introduceTeam")
    public String introduceTeam() {
        return "aboutUs/introduceTeam";
    }
	
	@RequestMapping("project")
    public String project() {
        return "aboutUs/project";
    }
	
	@RequestMapping("basket")
    public String basket() {
        return "loginInfo/basket";
    }
	
	@RequestMapping("productSell")
    public String productSell() {
        return "loginInfo/productSell";
    }
	
	@RequestMapping("productSellList")
    public String productSellList() {
        return "loginInfo/productSellList";
    }
	
	public String getURL(HttpServletRequest request) {
		Enumeration<?> param = request.getParameterNames();

		StringBuffer strParam = new StringBuffer();
		StringBuffer strURL = new StringBuffer();

		if (param.hasMoreElements()) {
			strParam.append("?");
		}

		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			String value = request.getParameter(name);

			strParam.append(name + "=" + value);

			if (param.hasMoreElements()) {
				strParam.append("&");
			}
		}

		strURL.append(request.getRequestURI());
		strURL.append(strParam);

		return strURL.toString();
	}
}
