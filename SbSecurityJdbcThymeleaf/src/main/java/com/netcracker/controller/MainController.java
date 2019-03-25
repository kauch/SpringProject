package com.netcracker.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.enums.RolesName;
import com.netcracker.enums.TypeMessage;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.services.impl.RolesServiceImpl;
import com.netcracker.services.impl.UsersServiceImpl;
import com.netcracker.utils.ServiceMail;
import com.netcracker.utils.WebUtils;

@Controller
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private RolesServiceImpl rolesService;

	@GetMapping(value = { "/", "/welcome" })
	public String welcomePage(Model model) {
		return "welcomePage";
	}

	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		return "loginPage";
	}

	@PostMapping(value = "/registration")
	public String registrationForm(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "password", required = false) String password, Model model)
			throws MessagingException {
		String resultRegistration = "registrationForm";
		if (username != null && userEmail != null && password != null) {
			Set<Roles> roleUser = new HashSet<>();
			roleUser.add(rolesService.getRoleByName(RolesName.ROLE_USER.name()));
			Users newUser = new Users();
			newUser.setUserName(username);
			newUser.setUserEmail(userEmail);
			newUser.setEncrytedPassword(password);
			newUser.setRoles(roleUser);
			try {
				usersService.saveUser(newUser);
				String info = "Mail sent to " + userEmail;
				model.addAttribute("info", info);
				ServiceMail mail = new ServiceMail();
				mail.send(newUser, TypeMessage.REGISTRATION);
				resultRegistration = successRegistrationPage(model);
			} catch (Exception e) {
				logger.info("Exception {}", e);
				resultRegistration = "registrationForm";
			}
		}
		return resultRegistration;
	}

	@GetMapping(value = "/registration")
	public String registrationForm(Model model) {
		return "registrationForm";
	}

	@GetMapping(value = "/logoutSuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@GetMapping(value = "/userInfo")
	public String userInfo(Model model, Principal principal) {
		String userName = principal.getName();
		logger.info("User Name:  {}", userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "userInfoPage";
	}

	@GetMapping(value = "/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403Page";
	}

	@GetMapping(value = "/registration/success")
	public String successRegistrationPage(Model model) {
		return "successRegistrationPage";
	}

	@GetMapping(value = "/map")
	public String getMap(Model model) {
		return "mapPage";
	}
}
