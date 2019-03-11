package com.netcracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Roles;
import com.netcracker.repositories.RolesRepository;
import com.netcracker.services.interfaces.IRolesService;

@Service
public class RolesService implements IRolesService {

	@Autowired
	RolesRepository rolesRepository;

	@Override
	public Roles getRoleByName(String roleName) {
		return rolesRepository.findByRoleName(roleName);
	}

	@Override
	public List<Roles> getAllRoles() {
		return rolesRepository.findAll();
	}

}
