package com.netcracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AppUser {
	 
    private Long userId;
    private String userName;
    private String encrytedPassword;
    private static Logger log = LogManager.getLogger(AppUser.class);
    
    public AppUser() {
 
    }
    
    public AppUser(Long userId, String userName, String encrytedPassword) {
    	this.userId = userId;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
    }
    
    public AppUser(String userName, String encrytedPassword) {
    	this.userId = uniqueID();
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
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
