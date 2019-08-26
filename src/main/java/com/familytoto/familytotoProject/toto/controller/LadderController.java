package com.familytoto.familytotoProject.toto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LadderController {
	@RequestMapping("/toto/ladder")
	public String goSadari() {
		return "/toto/ladder";
	}
}
