package com.familytoto.familytotoProject.changeCust.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ChangeCustController {
	@RequestMapping("changeCust")
    public String changeCust() {
        return "loginInfo/changeCust";
    }
}
