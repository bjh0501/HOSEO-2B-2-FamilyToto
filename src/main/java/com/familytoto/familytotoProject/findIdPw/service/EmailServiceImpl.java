package com.familytoto.familytotoProject.findIdPw.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.findIdPw.dao.FindIdPwDao;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender mailSender;
    
    @Autowired
    FindIdPwDao findIdPwDao;
    
	@Override
	public void sendEmail(final String to, final String title, final String contents) {
		 MimeMessage message = mailSender.createMimeMessage();
		  try {
			 MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			 messageHelper.setTo(to);
		      messageHelper.setSubject(title);
		      messageHelper.setText(contents, true);
		      mailSender.send(message);
		  } catch (Exception e) {
			  e.printStackTrace();
		}
	}
}