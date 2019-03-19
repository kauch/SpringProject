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
import com.netcracker.services.impl.OrderServiceImpl;
import com.netcracker.services.impl.UsersServiceImpl;
import com.netcracker.utils.WebUtils;

@Controller
public class OrderController {
	
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping(value = "/order")
	public String createOrderPage(@RequestParam(value = "OrderWeight", required = false) String orderWeight,
			@RequestParam(value = "Destination", required = false) String destination, Model model,
			Principal principal) {
		String resultCreateOrder = "createOrderPage";
		Users user = usersService.getUserByName(principal.getName());
		if(destination != null && orderWeight != null) {
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
		}
		return resultCreateOrder;
	}
	
	@GetMapping(value = "/order")
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
