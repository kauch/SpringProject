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

	public void send(String to) throws MessagingException, IOException {
		Session mailSession = Session.getDefaultInstance(setProperties());
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("testformydearprogram@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Confirm registration");

		String s = new String(
				Files.readAllBytes(Paths.get("src/main/resources/mail/textTemplate/successAutorize.html")));

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("<p>Hi, " + to + "!!!</p><br />" + s, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart, "text/html");

		Transport tr = mailSession.getTransport();
		tr.connect(null, "GUIKL_89_ubu3");
		tr.sendMessage(message, message.getAllRecipients());
		tr.close();
		logger.info("Message sending!");
	}

	public static void main(String[] args) throws MessagingException, IOException {
		ServiceMail mail = new ServiceMail();
		mail.send("kst.fis@gmail.com");
	}
}