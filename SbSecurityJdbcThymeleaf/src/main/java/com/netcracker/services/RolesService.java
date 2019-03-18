package com.netcracker.services;

import java.util.List;

import com.netcracker.model.Roles;

public interface RolesService {

	public Roles getRoleByName(String roleName);

	public List<Roles> getAllRoles();
	
	public Roles saveRole(Roles role);

}
