package com.netcracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AppUser {
	 
    private Long userId;
    @NotNull
    private String userName;
    @NotNull @Email
    private String userEmail;
    @NotNull
    private String encrytedPassword;
    
    private static Logger log = LogManager.getLogger(AppUser.class);
    
    public AppUser(Long userId, String userName, String userEmail, String encrytedPassword) {
    	this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.encrytedPassword = encrytedPassword;
    }
    
    public AppUser(String userName, String userEmail, String encrytedPassword) {
    	this.userId = uniqueID();
        this.userName = userName;
        this.userEmail = userEmail;
        this.encrytedPassword = encrytedPassword;
    }
    
    public AppUser() {
		// TODO Auto-generated constructor stub
	}

	public Long uniqueID() {
    	LocalDateTime id = LocalDateTime.now();
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("YYMMDDHHmmssSSS");
        String dataNew = fmt.format(id);
        Random myRandom = new Random();
        StringBuilder bld = new StringBuilder();
        bld.append(dataNew);
        for(int i = 0; i < 3; i++){
        	bld.append(myRandom.nextInt(9));
        }
        Long result = Long.valueOf(bld.toString());
        log.info("id new user = " + result);
        return result;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
 
    @Override
    public String toString() {
        return this.userName + "/" + this.encrytedPassword;
    }
 
}
