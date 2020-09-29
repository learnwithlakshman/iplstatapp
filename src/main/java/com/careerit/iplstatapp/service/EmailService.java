package com.careerit.iplstatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.from}")
	private String from;
	
	public void sendEmail(String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("Registration successful");
		message.setText("Your registration is successful, please continue to use application");
		mailSender.send(message);

	}
}
