package com.familytoto.familytotoProject.index.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.board.service.BoardService;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.scheduler.serivce.SportsTotoSchedulerService;
import com.google.gson.Gson;

@Controller
public class IndexController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	ExpService expService;
	
	@Autowired
	SportsTotoSchedulerService sportsTotoSchedulerService;
	
	@RequestMapping(value = { "/inesrtInSoccer" })
	@ResponseBody
    public String insert() {
		sportsTotoSchedulerService.inSoccer();
	
        return "insertSoccer";
    }
	
	@RequestMapping(value = { "/index", "/" })
    public String index(Model model) {
		model.addAttribute("notice", boardService.listNotice());
	
        return "/index";
    }
	
	
	@RequestMapping(value = "/header/levelInfo")
	@ResponseBody
    public String levelInfo(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		if(cVo == null) {
			return "";
		}
		
		Gson gson = new Gson();
		String sJson = gson.toJson(expService.getLevelInfo(cVo.getFamilyCustNo()));
		
        return sJson;
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
}
