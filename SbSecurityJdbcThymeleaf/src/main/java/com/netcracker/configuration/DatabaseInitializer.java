package com.netcracker.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.netcracker.enums.OrderStatus;
import com.netcracker.enums.RolesName;
import com.netcracker.model.Order;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.services.impl.OrderServiceImpl;
import com.netcracker.services.impl.RolesServiceImpl;
import com.netcracker.services.impl.UsersServiceImpl;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private RolesServiceImpl rolesService;

	@Autowired
	private OrderServiceImpl orderService;

	@Override
	public void run(String... args) throws Exception {
		Users user1 = new Users();
		Users user2 = new Users();
		Users user3 = new Users();

		createRolesAndUsers(user1, user2, user3);
		createOrders(user1, user2, user3);
	}

	private void createRolesAndUsers(Users user1, Users user2, Users user3) {
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
		admin.setLogin("Admin");
		admin.setUserEmail("Admin@myprj.kostya");
		admin.setEncrytedPassword("123");
		admin.setFirstName("Vova");
		admin.setSecondName("Pupkin");
		admin.setSex(true);
		admin.setRoles(adminSet);
		usersService.saveUser(admin);

		// Users user1 = new Users();
		user1.setLogin("User1");
		user1.setUserEmail("User1@myprj.kostya");
		user1.setEncrytedPassword("123");
		user1.setFirstName("Serezha");
		user1.setSecondName("Sidorov");
		user1.setSex(true);
		user1.setRoles(userSet);
		usersService.saveUser(user1);

//		Users user2 = new Users();
		user2.setLogin("User2");
		user2.setUserEmail("User2@myprj.kostya");
		user2.setEncrytedPassword("123");
		user2.setFirstName("Elena");
		user2.setSecondName("Premudraia");
		user2.setSex(false);
		user2.setRoles(userSet);
		usersService.saveUser(user2);

//		Users user3 = new Users();
		user3.setLogin("User3");
		user3.setUserEmail("User3@myprj.kostya");
		user3.setEncrytedPassword("123");
		user3.setFirstName("Test");
		user3.setSecondName("Testov");
		user3.setSex(true);
		user3.setRoles(userSet);
		usersService.saveUser(user3);

		Users manager = new Users();
		manager.setLogin("Manager");
		manager.setUserEmail("Manager@myprj.kostya");
		manager.setEncrytedPassword("123");
		manager.setFirstName("Sova");
		manager.setSecondName("Manager");
		manager.setSex(false);
		manager.setRoles(managerSet);
		usersService.saveUser(manager);

		Users courier = new Users();
		courier.setLogin("Courier");
		courier.setUserEmail("Courier@myprj.kostya");
		courier.setEncrytedPassword("123");
		courier.setFirstName("Yandex");
		courier.setSecondName("Eda");
		courier.setSex(true);
		courier.setRoles(courierSet);
		usersService.saveUser(courier);

		Users superAdmin = new Users();
		superAdmin.setLogin("SAdmin");
		superAdmin.setUserEmail("SAdmin@myprj.kostya");
		superAdmin.setEncrytedPassword("123");
		superAdmin.setFirstName("Super");
		superAdmin.setSecondName("Puper");
		superAdmin.setSex(true);
		superAdmin.setRoles(allRolesSet);
		usersService.saveUser(superAdmin);
	}

	private void createOrders(Users user1, Users user2, Users user3) {
		Order order1 = new Order();
		order1.setUser(user1);
		order1.setDestPoint("Липецк");
		order1.setWeight(4);
		order1.setStatus(OrderStatus.PENDING);
		orderService.saveOrder(order1);

		Order order2 = new Order();
		order2.setUser(user2);
		order2.setDestPoint("Тамбов");
		order2.setWeight(23);
		order2.setStatus(OrderStatus.SHIPPED);
		orderService.saveOrder(order2);

		Order order3 = new Order();
		order3.setUser(user3);
		order3.setDestPoint("Орел");
		order3.setWeight(13);
		order3.setStatus(OrderStatus.PENDING);
		orderService.saveOrder(order3);

		Order order4 = new Order();
		order4.setUser(user1);
		order4.setDestPoint("Тверь");
		order4.setWeight(21);
		order4.setStatus(OrderStatus.PENDING);
		orderService.saveOrder(order4);

		Order order5 = new Order();
		order5.setUser(user2);
		order5.setDestPoint("Санкт-Петербург");
		order5.setWeight(1);
		order5.setStatus(OrderStatus.PENDING);
		orderService.saveOrder(order5);

		Order order6 = new Order();
		order6.setUser(user3);
		order6.setDestPoint("Воронеж");
		order6.setWeight(45);
		order6.setStatus(OrderStatus.PENDING);
		orderService.saveOrder(order6);
	}

	private void createStorage() {

	}

}
