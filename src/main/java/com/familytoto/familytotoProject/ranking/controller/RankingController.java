package com.familytoto.familytotoProject.ranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.ranking.service.RankingService;

@Controller
public class RankingController {
	@Autowired
	RankingService rankingService;
	
	@RequestMapping("/ranking/exp")
	public String expRanking(Model model) {
		model.addAttribute("list", rankingService.listExpRanking());
		
		return "/ranking/exp";
	}
	
	@RequestMapping("/ranking/game")
	public String gamesRanking(Model model, String gameName) {
		if(gameName == null) {
			gameName = "LGS";
		}
		
		model.addAttribute("list", rankingService.listGameRanking(gameName));
		
		return "/ranking/game";
	}
}
