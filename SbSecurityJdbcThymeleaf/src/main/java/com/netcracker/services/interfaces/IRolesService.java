package com.netcracker.services.interfaces;

import java.util.List;

import com.netcracker.model.Roles;

public interface IRolesService {

	public Roles getRoleByName(String roleName);

	public List<Roles> getAllRoles();
	
	public Roles saveRole(Roles role);

}
