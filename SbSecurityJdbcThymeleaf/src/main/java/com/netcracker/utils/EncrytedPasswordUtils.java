package com.netcracker.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

	private static Logger log = LogManager.getLogger(EncrytedPasswordUtils.class.getName());

	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public static void main(String[] args) {
		String password = "123";
		String encrytedPassword = encrytePassword(password);
		log.info("Encryted Password: " + encrytedPassword);
	}
}