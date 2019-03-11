package com.netcracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.netcracker.enums.RolesName;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.repositories.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	private static Logger log = LogManager.getLogger(UserDetailsServiceImpl.class.getName());

	@Override
	public UserDetails loadUserByUsername(String login) {
		Users user = this.usersRepository.findByLogin(login);
		if (user == null) {
			log.info("User not found! " + login);
			throw new UsernameNotFoundException("User " + login + " was not found in the database");
		}

		log.info("Found User: " + user);

		Set<Roles> setRoles = user.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<>();
		if (setRoles != null) {
			for (Roles role : setRoles) {
				RolesName roleName = role.getRoleName();
				GrantedAuthority authority = new SimpleGrantedAuthority(roleName.name());
				grantList.add(authority);
				log.info("authority " + authority);
			}
		}

		return (UserDetails) new User(user.getLogin(), user.getEncrytedPassword(), grantList);
	}
}
