package com.familytoto.familytotoProject.registerCust.service;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

@Service
public class CaptchaServiceImpl implements CaptchaService {

	@Override
	public int isRight(HttpSession session, HttpServletRequest request) {
		String sCaptcha = request.getParameter("captcha");
		String answer = (String) session.getAttribute("captcha");
		
		if (sCaptcha.equals(answer)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void create(HttpServletResponse response, HttpSession session) {
		List<Font> fontList = new ArrayList<Font>();
		fontList.add(new Font("", Font.HANGING_BASELINE, 40));
		fontList.add(new Font("Courier", Font.ITALIC, 40));
		fontList.add(new Font("", Font.PLAIN, 80));

		List<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.black);
		
		Captcha captcha = new Captcha.Builder(500, 142).
				addText(new NumbersAnswerProducer(6), new DefaultWordRenderer(colorList, fontList)) // default: 5개의 숫자+문자
				.addNoise().addNoise().addNoise().addNoise().addNoise().addNoise() // 시야 방해 라인 3개
				.addNoise().addNoise().addNoise().addNoise().addNoise().addNoise()
				.addBackground() // 기본 하얀색 배경
				.build();

		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		response.setContentType("image/png");
		CaptchaServletUtil.writeImage(response, captcha.getImage()); // 이미지 그리기
		session.setAttribute("captcha", captcha.getAnswer()); // 값 저장
		
	}
	
}
