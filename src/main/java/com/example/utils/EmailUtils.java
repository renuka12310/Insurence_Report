package com.example.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean mailSender(String subject, String body, String to,File f) {
		
		
		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
			
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.setTo(to);
			helper.addAttachment("plans details", f);
			
			sender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	

}
