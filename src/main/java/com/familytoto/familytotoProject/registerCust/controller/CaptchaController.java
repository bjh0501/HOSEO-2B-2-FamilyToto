package com.familytoto.familytotoProject.registerCust.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.familytoto.familytotoProject.registerCust.service.CaptchaService;

import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.TextProducer;

@RequestMapping("/captcha")
@Controller
public class CaptchaController {
	@Autowired
	CaptchaService captchaService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index(HttpServletResponse response, HttpSession session) {
		captchaService.create(response, session);
	}
	
	@RequestMapping(value = "/voice", method = RequestMethod.GET)
	public void captchaAudio(HttpServletRequest request, HttpServletResponse response){
		captchaService.voice(request, response);
    }
}