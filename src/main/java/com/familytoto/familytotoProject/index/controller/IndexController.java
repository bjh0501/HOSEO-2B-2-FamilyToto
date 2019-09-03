package com.familytoto.familytotoProject.index.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.webLog.service.WebLogService;

@Controller
public class IndexController {
	@Inject
	WebLogService webLogService;
	
	@RequestMapping(value = { "index", "/" })
    public String index() {
        return "index";
    }
	
	@RequestMapping(value = "/history")
    public String history() {
        return "/aboutUs/history";
    }
	
	@RequestMapping(value ="/navereb68e2d272389e36f17ae3a3fe3d4437.html")
    public String naverSearchEngine() {
        return "/etc/searchEngine/navereb68e2d272389e36f17ae3a3fe3d4437.html";
    }
	
	@RequestMapping("itemShop")
    public String itemShop() {
        return "shop/itemShop";
    }
	
	@RequestMapping("editor")
    public String editor() {
        return "border/editor/editor";
    }
	
	
	
	@RequestMapping("introduceTeam")
    public String introduceTeam() {
        return "aboutUs/introduceTeam";
    }
	
	@RequestMapping("project")
    public String project() {
        return "aboutUs/project";
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
