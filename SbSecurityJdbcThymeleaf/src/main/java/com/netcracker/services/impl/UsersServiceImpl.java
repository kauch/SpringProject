package com.netcracker.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Users;
import com.netcracker.repositories.UsersRepository;
import com.netcracker.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public Users getUserByName(String userName) {
		return usersRepository.findByUserName(userName);
	}

	@Override
	public Users saveUser(Users user) {
		return usersRepository.saveAndFlush(user);
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
}
