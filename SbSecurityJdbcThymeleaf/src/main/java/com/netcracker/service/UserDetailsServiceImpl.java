package com.netcracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.netcracker.dao.AppRoleDAO;
import com.netcracker.dao.AppUserDAO;
import com.netcracker.model.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAO appUserDAO;
 
    @Autowired
    private AppRoleDAO appRoleDAO;
 
    private static Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);
 
        if (appUser == null) {
            log.info("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        log.info("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
 
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
                log.info("authority " + authority);
            }
        }
 
        return (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);
    }

}
