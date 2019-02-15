package com.netcracker.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceMail {
	private static Logger log = LogManager.getLogger(ServiceMail.class);
	
	//private static ServiceMail tlsSender = new ServiceMail("testformydearprogram@gmail.com", "wero58_hop7");
	//tlsSender.send("This is Subject", "TLS: This is text!", "testformydearprogram@gmail.com", "kst.fis@gmail.com");
	 public static void main(String args[]) {
	        final String SMTP_HOST = "smtp.gmail.com";
	        final String SMTP_PORT = "587";
	        final String GMAIL_USERNAME = "testformydearprogram";
	        final String GMAIL_PASSWORD = "wero58_hop7";

	        System.out.println("Process Started");

	        Properties prop = System.getProperties();
	        prop.setProperty("mail.smtp.starttls.enable", "true");
	        prop.setProperty("mail.smtp.host", SMTP_HOST);
	        prop.setProperty("mail.smtp.user", GMAIL_USERNAME);
	        prop.setProperty("mail.smtp.password", GMAIL_PASSWORD);
	        prop.setProperty("mail.smtp.port", SMTP_PORT);
	        prop.setProperty("mail.smtp.auth", "true");
	        System.out.println("Props : " + prop);

	        Session session = Session.getInstance(prop, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(GMAIL_USERNAME,
	                        GMAIL_PASSWORD);
	            }
	        });

	        System.out.println("Got Session : " + session);

	        MimeMessage message = new MimeMessage(session);
	        try {
	            System.out.println("before sending");
	            message.setFrom(new InternetAddress("testformydearprogram@gmail.com"));
	            message.addRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse("kst.fis@gmail.com"));
	            message.setSubject("My First Email Attempt from Java");
	            message.setText("Hi, This mail came from Java Application.");
	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse("kst.fis@gmail.com"));
	            Transport transport = session.getTransport("smtp");
	            System.out.println("Got Transport" + transport);
	            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            System.out.println("message Object : " + message);
	            System.out.println("Email Sent Successfully");
	        } catch (AddressException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (MessagingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

}
