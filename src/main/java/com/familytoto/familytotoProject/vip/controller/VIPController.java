package com.familytoto.familytotoProject.vip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VIPController {
	@RequestMapping(value = { "/vipVasic"})
    public String index() {
        return "/viproom/vipVasic";
    }
	
	@RequestMapping(value = { "/vipEnt"})
    public String vipEnt() {
        return "/viproom/vipEnt";
    }
	
	@RequestMapping(value = { "/vipIntro"})
    public String vipIntro() {
        return "/viproom/vipIntro";
    }
	
	@RequestMapping(value = { "/vipOpenrr"})
    public String vipOpenrr() {
        return "/viproom/vipOpenrr";
    }
	
}
