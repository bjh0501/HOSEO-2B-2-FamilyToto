package com.familytoto.familytotoProject.toto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {
	@RequestMapping("/toto/graph")
	public String goGraph() {
		return "/toto/graph";
	}
}
