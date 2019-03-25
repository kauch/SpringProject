package com.netcracker.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netcracker.model.Users;
import com.netcracker.services.impl.UsersServiceImpl;

@Controller
public class IdentityManagementController {
	
	public static final Logger logger = LoggerFactory.getLogger(IdentityManagementController.class);
	
	@Autowired
	private UsersServiceImpl usersService;
	
	@GetMapping(value = "/admin")
	public String adminPage(Model model, Principal principal) {
		model.addAttribute("usersList", usersService.getAllUsers());
		return "adminPage";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Users user = usersService.getUserById(id);
	    model.addAttribute("user", user);
	    return "userEditPage";
	}
}
