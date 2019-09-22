package com.familytoto.familytotoProject.vip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VIPController {
	@RequestMapping(value = { "/vip"})
    public String index() {
        return "/viproom/vipVasic";
    }
}
