package com.netcracker.services;

import java.util.List;

import com.netcracker.model.Users;

public interface UsersService {

	public Users getUserByLogin(String login);

	public Users getUserById(Long userId);

	public Users saveUser(Users user);

	public void deleteUser(Users user);

	public List<Users> getAllUsers();
}
