package com.netcracker.controller;

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

import com.netcracker.model.Users;
import com.netcracker.services.impl.UsersServiceImpl;

@Controller
public class IdentityManagementController {

	public static final Logger logger = LoggerFactory.getLogger(IdentityManagementController.class);

	@Autowired
	private UsersServiceImpl usersService;

	@GetMapping(value = "/admin")
	public String adminPage(Model model) {
		model.addAttribute("usersList", usersService.getAllUsers());
		return "adminPage";
	}

	@GetMapping(value = "admin/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Users user = usersService.getUserById(id);
		model.addAttribute("user", user);
		return "userEditPage";
	}

	@PostMapping(value = "admin/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "userEmail", required = false) String email,
			@RequestParam(value = "userFirstName", required = false) String firstName,
			@RequestParam(value = "userSecondName", required = false) String secondName, @Valid Users user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setUserId(id);
			return "userEditPage";
		}
		Users edit = usersService.getUserById(id);
		edit.setUserEmail(email);
		edit.setFirstName(firstName);
		edit.setSecondName(secondName);
		usersService.saveUser(edit);
		model.addAttribute("usersList", usersService.getAllUsers());
		return "redirect:/admin";
	}

	@GetMapping(value = "admin/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Users user = usersService.getUserById(id);
		usersService.deleteUser(user);
		model.addAttribute("usersList", usersService.getAllUsers());
		return "redirect:/admin";
	}
}
