package com.netcracker.services.interfaces;

import java.util.List;

import com.netcracker.model.Users;

public interface IUsersService {

	public Users getUserByName(String userName);

	public Users saveUser(Users user);

	public List<Users> getAllUsers();
}
