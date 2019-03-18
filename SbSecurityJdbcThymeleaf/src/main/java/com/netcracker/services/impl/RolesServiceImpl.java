package com.netcracker.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Roles;
import com.netcracker.repositories.RolesRepository;
import com.netcracker.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesRepository rolesRepository;

	@Override
	public List<Roles> getAllRoles() {
		return rolesRepository.findAll();
	}

	@Override
	public Roles saveRole(Roles role) {
		return rolesRepository.saveAndFlush(role);
	}

	@Override
	public Roles getRoleByName(String roleName) {
		return rolesRepository.findByRoleName(roleName);
	}

}
