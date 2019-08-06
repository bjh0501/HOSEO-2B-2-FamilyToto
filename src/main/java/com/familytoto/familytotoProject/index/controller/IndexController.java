package com.familytoto.familytotoProject.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.mybatis.webLog.controller.WebLogController;
import com.familytoto.familytotoProject.mybatis.webLog.domain.WebLogVO;

@Controller
public class IndexController {
	WebLogController webLogController;
	
	HttpServletRequest httpServletRequest;
	
	@RequestMapping("index")
    public String index() {
        return "index";
    }
	
	@RequestMapping("login")
    public String login() {
		System.out.println("메인 호출@#$@#$");
		System.out.println(webLogController.insertWebLog(httpServletRequest));
        return "loginInfo/login";
    }
	
	@RequestMapping("registerCust")
    public String registerCust() {
        return "loginInfo/registerCust";
    }
	
	@RequestMapping("findID_PW")
    public String findId_Pw() {
        return "loginInfo/findID_PW";
    }
	
	@RequestMapping("changeCust")
    public String changeCust() {
        return "loginInfo/changeCust";
    }
	
	@RequestMapping("charge")
    public String charge() {
        return "shop/charge";
    }
	
	@RequestMapping("itemShop")
    public String itemShop() {
        return "shop/itemShop";
    }
	
	@RequestMapping("border")
    public String border() {
        return "border/border";
    }
	
	@RequestMapping("registerBorder")
    public String registerBorder() {
        return "border/registerBorder";
    }
	
	@RequestMapping("editor")
    public String editor() {
        return "border/editor/editor";
    }
	
	@RequestMapping("showEditor")
    public String showEditor() {
        return "border/editor/showEditor";
    }
	
	@RequestMapping("showBorder")
    public String showBorder() {
        return "border/showBorder";
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
}
