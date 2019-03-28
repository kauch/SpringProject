package com.netcracker.services.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.repositories.UsersRepository;
import com.netcracker.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	public static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersRepository usersRepository;

	@Override
	public Users getUserByLogin(String login) {
		return usersRepository.findByLogin(login);
	}

	@Override
	public void saveUser(Users user) {
		usersRepository.saveAndFlush(user);
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Users getUserById(Long userId) {
		return usersRepository.findByUserId(userId);
	}

	@Override
	public void deleteUser(Users user) {
		usersRepository.delete(user);
	}

	@Override
	public void deleteRoleForUser(Users user, Roles role) {
		Set<Roles> roles = user.getRoles();
		roles.remove(role);
		user.setRoles(roles);
		usersRepository.saveAndFlush(user);
	}
}
