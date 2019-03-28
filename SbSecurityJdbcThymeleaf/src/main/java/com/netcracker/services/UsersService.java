package com.netcracker.services;

import java.util.List;

import com.netcracker.model.Roles;
import com.netcracker.model.Users;

public interface UsersService {

	public Users getUserByLogin(String login);

	public Users getUserById(Long userId);

	public void saveUser(Users user);

	public void deleteUser(Users user);

	public void deleteRoleForUser(Users user, Roles role);

	public List<Users> getAllUsers();
}
