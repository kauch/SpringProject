package com.netcracker.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.netcracker.enums.RolesName;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.services.RolesService;
import com.netcracker.services.UsersService;

@Component
public class DatabaseInitializer implements CommandLineRunner {
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	
	@Override
	public void run(String... args) throws Exception {
		createRolesAndUsers();
	}

	private void createRolesAndUsers() {
		Roles testRole1 = new Roles();
		testRole1.setRoleName(RolesName.ROLE_ADMIN.name());
		rolesService.saveRole(testRole1);

		Roles testRole2 = new Roles();
		testRole2.setRoleName(RolesName.ROLE_USER.name());
		rolesService.saveRole(testRole2);

		Set<Roles> adminSet = new HashSet<>();
		adminSet.add(testRole1);
		Set<Roles> userSet = new HashSet<>();
		userSet.add(testRole2);

		Users testUser1 = new Users();
		testUser1.setUserName("dbadmin1");
		testUser1.setUserEmail("testAdmin@myprj.kostya");
		testUser1.setEncrytedPassword("123");
		testUser1.setRoles(adminSet);
		usersService.saveUser(testUser1);

		Users testUser2 = new Users();
		testUser2.setUserName("dbuser1");
		testUser2.setUserEmail("testUser@myprj.kostya");
		testUser2.setEncrytedPassword("123");
		testUser2.setRoles(userSet);
		usersService.saveUser(testUser2);
	}
}
