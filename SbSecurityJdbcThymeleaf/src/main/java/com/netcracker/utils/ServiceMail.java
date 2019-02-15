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
	//sendMailForMyProgram@yandex.ru
	//wero58_hop7
	//testformydearprogram@gmail.com 
	
	public void send(String to) throws AddressException, MessagingException
	{
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sendMailForMyProgram", "wero58_hop7");
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sendMailForMyProgram@yandex.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Subject");
        message.setText("Text");
        
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.yandex.ru", "sendMailForMyProgram", "wero58_hop7");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        Transport.send(message);
	}
}