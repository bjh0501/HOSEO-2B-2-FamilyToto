package com.familytoto.familytotoProject.toto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DiceController {
	@RequestMapping("/toto/dice")
	public String goDice() {
		return "/toto/dice";
	}
}
