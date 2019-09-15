package com.familytoto.familytotoProject.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.board.service.BoardService;

@Controller
public class IndexController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = { "/index", "/" })
    public String index(Model model) {
		model.addAttribute("notice", boardService.listNotice());
	
        return "/index";
    }
	
	@RequestMapping(value = "/history")
    public String history() {
        return "/aboutUs/history";
    }
	
	@RequestMapping(value ="/navereb68e2d272389e36f17ae3a3fe3d4437.html")
    public String naverSearchEngine() {
        return "/etc/searchEngine/navereb68e2d272389e36f17ae3a3fe3d4437.html";
    }
	
	@RequestMapping("/editor")
    public String editor() {
        return "/border/editor/editor";
    }
	
	
	
	@RequestMapping("/introduceTeam")
    public String introduceTeam() {
        return "/aboutUs/introduceTeam";
    }
	
	@RequestMapping("/project")
    public String project() {
        return "/aboutUs/project";
    }
	
	@RequestMapping("/productSellList")
    public String productSellList() {
        return "/loginInfo/productSellList";
    }
}
