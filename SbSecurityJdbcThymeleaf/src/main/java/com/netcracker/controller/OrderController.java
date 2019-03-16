package com.netcracker.controller;

import java.security.Principal;

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

import com.netcracker.enums.OrderStatus;
import com.netcracker.model.Order;
import com.netcracker.model.Users;
import com.netcracker.services.OrderService;
import com.netcracker.services.UsersService;
import com.netcracker.utils.WebUtils;

@Controller
public class OrderController {
	
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/createOrder")
	public String createOrderPage(@RequestParam(value = "OrderWeight", required = false) String orderWeight,
			@RequestParam(value = "Destination", required = false) String destination, Model model,
			Principal principal) {
		String resultCreateOrder;
		Users user = usersService.getUserByName(principal.getName());
		try {
			int weight = Integer.parseInt(orderWeight);
			Order newOrder = new Order();
			newOrder.setUser(user);
			newOrder.setDestPoint(destination);
			newOrder.setWeight(weight);
			newOrder.setStatus(OrderStatus.PENDING);
			orderService.saveOrder(newOrder);
			resultCreateOrder = "redirect:/myOrders";
		}
		catch(Exception e) {
			resultCreateOrder = "createOrderPage";
		}
		return resultCreateOrder;
	}
	
	@GetMapping(value = "/createOrder")
	public String viewOrderPage(Model model) {
		return "createOrderPage";
	}
	
	@GetMapping(value = "/myOrders")
	public String myOrders(Model model, Principal principal) {
		String userName = principal.getName();
		logger.info("User Name:  {}", userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("ordersList", orderService.getAllOrdersForUser(usersService.getUserByName(userName)));
		return "myOrdersPage";
	}
	
	@GetMapping(value = "/allOrders")
	public String allOrders(Model model, Principal principal) {
		String userName = principal.getName();
		logger.info("User Name:  {}", userName);
		model.addAttribute("ordersList", orderService.getAllOrders());
		return "allOrdersPage";
	}
}
