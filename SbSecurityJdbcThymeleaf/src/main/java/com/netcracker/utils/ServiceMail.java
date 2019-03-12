package com.netcracker.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceMail {
	public static final Logger logger = LoggerFactory.getLogger(ServiceMail.class);
	/**
	 * sendMailForMyProgram@yandex.ru  
	 * GUIKL_89_ubu3
	 * testformydearprogram@gmail.com
	 */

	public void send(String to) throws MessagingException {
		final Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.auth", "true");
		properties.put("mail.smtps.host", "smtp.gmail.com");
		properties.put("mail.smtps.user", "testformydearprogram@gmail.com");

		Session mailSession = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("testformydearprogram@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Confirm registration");
		message.setText("Welcome!");

		Transport tr = mailSession.getTransport();
		tr.connect(null, "GUIKL_89_ubu3");
		tr.sendMessage(message, message.getAllRecipients());
		tr.close();
		logger.info("Message sending!");
	}
}