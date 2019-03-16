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
		Set<Roles> allRolesSet = new HashSet<>();
		allRolesSet.add(adminRole);
		allRolesSet.add(userRole);
		allRolesSet.add(managerRole);
		allRolesSet.add(courierRole);		
		
		Users admin = new Users();
		admin.setUserName("Admin");
		admin.setUserEmail("Admin@myprj.kostya");
		admin.setEncrytedPassword("123");
		admin.setRoles(adminSet);
		usersService.saveUser(admin);

		Users user1 = new Users();
		user1.setUserName("User1");
		user1.setUserEmail("User1@myprj.kostya");
		user1.setEncrytedPassword("123");
		user1.setRoles(userSet);
		usersService.saveUser(user1);
		
		Users user2 = new Users();
		user2.setUserName("User2");
		user2.setUserEmail("User2@myprj.kostya");
		user2.setEncrytedPassword("123");
		user2.setRoles(userSet);
		usersService.saveUser(user2);
		
		Users user3 = new Users();
		user3.setUserName("User3");
		user3.setUserEmail("User3@myprj.kostya");
		user3.setEncrytedPassword("123");
		user3.setRoles(userSet);
		usersService.saveUser(user3);
		
		Users manager = new Users();
		manager.setUserName("Manager");
		manager.setUserEmail("Manager@myprj.kostya");
		manager.setEncrytedPassword("123");
		manager.setRoles(managerSet);
		usersService.saveUser(manager);

		Users courier = new Users();
		courier.setUserName("Courier");
		courier.setUserEmail("Courier@myprj.kostya");
		courier.setEncrytedPassword("123");
		courier.setRoles(courierSet);
		usersService.saveUser(courier);
		
		Users superAdmin = new Users();
		superAdmin.setUserName("SAdmin");
		superAdmin.setUserEmail("SAdmin@myprj.kostya");
		superAdmin.setEncrytedPassword("123");
		superAdmin.setRoles(allRolesSet);
		usersService.saveUser(superAdmin);
	}
	
	private void createOrders() {
		
	}
	
	private void createStorage() {
		
	}
	
}
