package com.netcracker.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.model.Order;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.services.impl.OrderServiceImpl;
import com.netcracker.services.impl.RolesServiceImpl;
import com.netcracker.services.impl.UsersServiceImpl;

@Controller
public class IdentityManagementController {

	public static final Logger logger = LoggerFactory.getLogger(IdentityManagementController.class);

	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private RolesServiceImpl rolesService;

	@GetMapping(value = "/admin")
	public String adminPage(Model model) {
		model.addAttribute("usersList", usersService.getAllUsers());
		return "adminPage";
	}

	@GetMapping(value = "admin/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model modelUser, Model modelRoles,
			Model modelSelectRoles) {
		Users user = usersService.getUserById(id);
		Set<Roles> roles = user.getRoles();
		List<Roles> listRoles = rolesService.getAllRoles();

		Iterator<Roles> iterator = roles.iterator();
		while (iterator.hasNext()) {
			listRoles.remove(iterator.next());
		}

		modelUser.addAttribute("user", user);
		modelRoles.addAttribute("roles", roles);
		modelSelectRoles.addAttribute("listRoles", listRoles);
		return "userEditPage";
	}

	@PostMapping(value = "admin/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "userEmail", required = false) String email,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "exampleRadios", required = false) boolean gender, @Valid Users user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userEditPage";
		}
		logger.info("{}", gender);
		Users edit = usersService.getUserById(id);
		edit.setUserEmail(email);
		edit.setFirstName(firstName);
		edit.setLastName(lastName);
		edit.setGender(gender);
		usersService.saveUser(edit);
		model.addAttribute("usersList", usersService.getAllUsers());
		return "redirect:/admin";
	}

	@GetMapping(value = "admin/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Users user = usersService.getUserById(id);
		List<Order> orders = orderService.getAllOrdersForUser(user);
		orders.forEach(m -> orderService.deleteOrder(m));
		usersService.deleteUser(user);
		model.addAttribute("usersList", usersService.getAllUsers());
		return "redirect:/admin";
	}

	@GetMapping(value = "admin/edit/delete/{id}-{name-rol}")
	public String deleteRoleForUser(@PathVariable("id") long id, @PathVariable("name-rol") String roleName,
			Model model) {
		Users user = usersService.getUserById(id);
		logger.info("user {} {}", id, roleName);
		Roles role = rolesService.getRoleByName(roleName);
		logger.info("role {} {}", id, role);
		usersService.deleteRoleForUser(user, role);
		logger.info("delete {} {}", id, role);
		return "redirect:/admin/edit/{id}";
	}

	@PostMapping(value = "admin/edit/add-roles/{id}")
	public String addRoleForUser(@PathVariable("id") long id,
			@RequestParam(value = "inputGroupSelect", required = false) Roles role, Model model) {
		if (role != null) {
			try {
				logger.info("userId = {} roleName = {}", id, role.getRoleName());
				Users user = usersService.getUserById(id);
				Set<Roles> setRole = user.getRoles();
				setRole.add(role);
				user.setRoles(setRole);
				usersService.saveUser(user);
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
		return "redirect:/admin/edit/{id}";
	}
}
