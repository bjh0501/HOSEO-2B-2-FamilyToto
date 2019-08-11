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
        String getAnswer = (String) request.getSession().getAttribute("captcha");
        AudioCaptcha ac = new AudioCaptcha.Builder()
        .addAnswer(new SetTextProducer(getAnswer))
        .addVoice()
        .addNoise()
        .build();
        
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
    
        CaptchaServletUtil.writeAudio(response,  ac.getChallenge());;
        request.getSession().setAttribute("captcha", ac.getAnswer());
    }
}

class SetTextProducer implements TextProducer {

    private final String srcStr;

    public SetTextProducer(String answer) {    
        srcStr = answer; 
   }

   

    //@Override
   public String getText() {        
        return srcStr;
   }
}