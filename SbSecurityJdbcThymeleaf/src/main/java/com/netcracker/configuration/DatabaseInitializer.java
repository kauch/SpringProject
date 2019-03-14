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
		Roles adminRole = new Roles();
		adminRole.setRoleName(RolesName.ROLE_ADMIN.name());
		rolesService.saveRole(adminRole);
		Roles userRole = new Roles();
		userRole.setRoleName(RolesName.ROLE_USER.name());
		rolesService.saveRole(userRole);
		Roles managerRole = new Roles();
		managerRole.setRoleName(RolesName.ROLE_MANAGER.name());
		rolesService.saveRole(managerRole);
		Roles courierRole = new Roles();
		courierRole.setRoleName(RolesName.ROLE_COURIER.name());
		rolesService.saveRole(courierRole);

		Set<Roles> adminSet = new HashSet<>();
		adminSet.add(adminRole);
		Set<Roles> userSet = new HashSet<>();
		userSet.add(userRole);
		Set<Roles> managerSet = new HashSet<>();
		managerSet.add(managerRole);
		Set<Roles> courierSet = new HashSet<>();
		courierSet.add(courierRole);

		Users admin = new Users();
		admin.setUserName("dbAdmin");
		admin.setUserEmail("testAdmin@myprj.kostya");
		admin.setEncrytedPassword("123");
		admin.setRoles(adminSet);
		usersService.saveUser(admin);

		Users user = new Users();
		user.setUserName("dbUser");
		user.setUserEmail("testUser@myprj.kostya");
		user.setEncrytedPassword("123");
		user.setRoles(userSet);
		usersService.saveUser(user);
		
		Users manager = new Users();
		manager.setUserName("dbManager");
		manager.setUserEmail("testManager@myprj.kostya");
		manager.setEncrytedPassword("123");
		manager.setRoles(managerSet);
		usersService.saveUser(manager);

		Users courier = new Users();
		courier.setUserName("dbCourier");
		courier.setUserEmail("testCourier@myprj.kostya");
		courier.setEncrytedPassword("123");
		courier.setRoles(courierSet);
		usersService.saveUser(courier);
	}
	
	private void createOrders() {
		
	}
	
	private void createStorage() {
		
	}
	
}
