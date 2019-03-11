package com.netcracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Users;
import com.netcracker.repositories.UsersRepository;
import com.netcracker.services.interfaces.IUsersService;

@Service
public class UsersService implements IUsersService {

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
}
