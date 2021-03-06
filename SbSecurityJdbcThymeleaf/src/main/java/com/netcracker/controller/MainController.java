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
import com.netcracker.services.impl.OrderServiceImpl;
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
	
	@Autowired
	private OrderServiceImpl orderService;

	@GetMapping(value = { "/", "/welcome" })
	public String welcomePage(Model model) {
		return "welcomePage";
	}

	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		return "loginPage";
	}

	@PostMapping(value = "/registration")
	public String registrationForm(@RequestParam(value = "login", required = false) String login,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "exampleRadios", required = false) boolean gender, Model model)
			throws MessagingException {
		String resultRegistration = "registrationForm";
		logger.info("begin user {} {} {} {} {}", login, userEmail, firstName, lastName, gender);
		if (!"".equals(login) && !"".equals(userEmail) && !"".equals(password) && !"".equals(firstName)
				&& !"".equals(lastName)) {
			Set<Roles> roleUser = new HashSet<>();
			roleUser.add(rolesService.getRoleByName(RolesName.ROLE_USER.name()));
			Users newUser = new Users();
			newUser.setLogin(login);
			newUser.setUserEmail(userEmail);
			newUser.setEncrytedPassword(password);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setGender(gender);
			newUser.setRoles(roleUser);
			logger.info("user {} {} {} {} {}", login, userEmail, firstName, lastName, gender);
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
	public String userInfo(Model modelInfoUser, Model modelInfoOrders, Principal principal) {
		String login = principal.getName();
		logger.info("User Name:  {}", login);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		modelInfoUser.addAttribute("userInfo", userInfo);
		modelInfoOrders.addAttribute("ordersList", orderService.getAllOrdersForUser(usersService.getUserByLogin(login)));
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

}
