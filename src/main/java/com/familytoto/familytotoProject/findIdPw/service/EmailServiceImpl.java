package com.familytoto.familytotoProject.findIdPw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.findIdPw.dao.FindIdPwDao;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    FindIdPwDao findIdPwDao;
    
	@Override
	public void sendEmail(final String to, final String title, final String contents) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(contents);
        emailSender.send(message);
	}
}