package com.familytoto.familytotoProject.qna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaController {
	@RequestMapping("/qna")
    public String qna() {
        return "/board/qna";
    }
	
	@RequestMapping("/qna/register")
    public String qnaRegister() {
        return "/board/qna";
    }
}
