package com.netcracker.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netcracker.enums.TypeMessage;
import com.netcracker.model.Users;

public class ServiceMail {
	public static final Logger logger = LoggerFactory.getLogger(ServiceMail.class);

	/**
	 * sendMailForMyProgram@yandex.ru GUIKL_89_ubu3 testformydearprogram@gmail.com
	 * 
	 * @throws IOException
	 */

	private Properties setProperties() {
		final Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.auth", "true");
		properties.put("mail.smtps.host", "smtp.gmail.com");
		properties.put("mail.smtps.user", "testformydearprogram@gmail.com");
		return properties;
	}

	public void send(Users user, TypeMessage type) throws MessagingException, IOException {
		Session mailSession = Session.getDefaultInstance(setProperties());
		MimeMessage message = new MimeMessage(mailSession);
		if(type.equals(TypeMessage.REGISTRATION)) {
			message = createMessageRegistration(message, user);
		}
		Transport tr = mailSession.getTransport();
		tr.connect(null, "GUIKL_89_ubu3");
		tr.sendMessage(message, message.getAllRecipients());
		tr.close();
		logger.info("Message sending!");
	}
	
	public MimeMessage createMessageRegistration(MimeMessage message, Users user) throws MessagingException, IOException {
		message.setFrom(new InternetAddress("testformydearprogram@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserEmail()));
		message.setSubject("Confirm registration");
		String templateMessage = new String(Files.readAllBytes(Paths.get("src/main/resources/mail/textTemplate/successAutorize.html")));

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("<p>Hi, " + user.getUserName() + "!!!</p><br />" + templateMessage, "text/html;charset=UTF-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart, "text/html;charset=UTF-8");
		return message;
	}
}