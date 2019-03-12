package com.netcracker.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.enums.OrderStatus;
import com.netcracker.model.Order;
import com.netcracker.model.Users;
import com.netcracker.services.OrderService;
import com.netcracker.services.UsersService;

@Controller
public class OrderController {
	
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/createOrder")
	public String createOrderPage(@RequestParam(value = "OrderWeight", required = false) String orderWeight,
			@RequestParam(value = "Destination", required = false) String destination, Model model,
			Principal principal) {
		String resultCreateOrder;
		String userName = principal.getName();
		Users user = usersService.getUserByName(userName);
		try {
			int weight = Integer.parseInt(orderWeight);
			Order newOrder = new Order();
			newOrder.setUser(user);
			newOrder.setDestPoint(destination);
			newOrder.setWeight(weight);
			newOrder.setStatus(OrderStatus.PENDING);
			orderService.saveOrder(newOrder);
			resultCreateOrder = "redirect:/userInfo";
		}
		catch(Exception e) {
			resultCreateOrder = "createOrderPage";
		}
		return resultCreateOrder;
	}
}
