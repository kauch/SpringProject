package com.netcracker.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.model.Order;
import com.netcracker.model.Roles;
import com.netcracker.model.Users;
import com.netcracker.repositories.OrderRepository;
import com.netcracker.repositories.RolesRepository;
import com.netcracker.repositories.UsersRepository;
import com.netcracker.utils.ServiceMail;
import com.netcracker.utils.WebUtils;

@Controller
public class MainController {

	private static Logger log = LogManager.getLogger(MainController.class.getName());

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@GetMapping(value = { "/", "/welcome" })
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "welcomePage";
	}

	@GetMapping(value = "/admin")
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("usersList", usersRepository.findAll());
		return "adminPage";
	}

	@GetMapping(value = "/login")
	public String loginPage(Model model) {

		return "loginPage";
	}

	@GetMapping(value = "/registration")
	public String registrationForm(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "password", required = false) String password, Model model)
			throws MessagingException {
		String resultRegistration = "registrationForm";

		if (username != null && userEmail != null && password != null) {
			Set<Roles> roleUser = new HashSet<>();
			roleUser.add(rolesRepository.findByRoleName("ROLE_USER"));
			Users newUser = new Users();
			newUser.setUserName(username);
			newUser.setUserEmail(userEmail);
			newUser.setEncrytedPassword(password);
			newUser.setRoles(roleUser);
			try {
				usersRepository.save(newUser);
				resultRegistration = "successRegistrationPage";
				String info = "Mail sent to " + userEmail;
				model.addAttribute("info", info);
				ServiceMail mail = new ServiceMail();
				mail.send(userEmail);
			} catch (Exception e) {
				resultRegistration = "registrationForm";
			}
		}
		return resultRegistration;
	}

	@GetMapping(value = "/logoutSuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@GetMapping(value = "/userInfo")
	public String userInfo(Model model, Principal principal) {
		String userName = principal.getName();
		log.info("User Name: " + userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("ordersList", orderRepository.findByUser(usersRepository.findByUserName(userName)));
		return "userInfoPage";
	}

	@GetMapping(value = "/createOrder")
	public String createOrderPage(@RequestParam(value = "OrderWeight", required = false) String orderWeight,
			@RequestParam(value = "Destination", required = false) String destination, Model model,
			Principal principal) {
		String resultCreateOrder = "createOrderPage";
		String userName = principal.getName();
		Users user = usersRepository.findByUserName(userName);
		if (orderWeight != null && destination != null) {
			int weight = Integer.parseInt(orderWeight);
			Order newOrder = new Order();
			newOrder.setUser(user);
			newOrder.setDestPoint(destination);
			newOrder.setWeight(weight);
			orderRepository.save(newOrder);
			resultCreateOrder = userInfo(model, principal);
		}
		return resultCreateOrder;
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
}
