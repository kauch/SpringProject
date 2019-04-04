package com.netcracker.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.enums.OrderStatus;
import com.netcracker.model.Order;
import com.netcracker.model.Users;
import com.netcracker.services.impl.OrderServiceImpl;
import com.netcracker.services.impl.UsersServiceImpl;

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
		Users user = usersService.getUserByLogin(principal.getName());
		if (destination != null && orderWeight != null) {
			try {
				int weight = Integer.parseInt(orderWeight);
				Order newOrder = new Order();
				newOrder.setUser(user);
				newOrder.setDestPoint(destination);
				newOrder.setWeight(weight);
				newOrder.setStatus(OrderStatus.PENDING);
				orderService.saveOrder(newOrder);
				resultCreateOrder = "redirect:/userInfo";
			} catch (Exception e) {
				resultCreateOrder = "createOrderPage";
			}
		}
		return resultCreateOrder;
	}

	@GetMapping(value = "/order")
	public String viewOrderPage(Model model) {
		return "createOrderPage";
	}

	@GetMapping(value = "order/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Order order = orderService.getOrderById(id);
		model.addAttribute("order", order);
		return "orderEditPage";
	}
	
	@PostMapping(value = "order/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id,
			@RequestParam(value = "weight", required = false) String orderWeight,
			@RequestParam(value = "destPoint", required = false) String destination, 
			Model model, HttpServletRequest reqest, Principal principal) {
		String path = "redirect:/userInfo";
		
		Order order = orderService.getOrderById(id);
		//model.addAttribute("order", order);
		logger.info("{} {} {}", orderWeight, destination, order);
		if (destination != null && orderWeight != null) {
			try {
				int weight = Integer.parseInt(orderWeight);
				order.setDestPoint(destination);
				order.setWeight(weight);
				logger.info("{} {} {}", orderWeight, destination, order);
				orderService.saveOrder(order);
			} catch (Exception e) {
				path = "createEditPage";
			}
		}
		return path;
	}

	@GetMapping(value = "/all-orders")
	public String allOrders(Model model) {
		model.addAttribute("ordersList", orderService.getAllOrders());
		return "allOrdersPage";
	}

	@GetMapping(value = "order/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model, HttpServletRequest reqest, Principal principal) {
		String path = "redirect:";
		String login = principal.getName();
		String newPath;
		Order order = orderService.getOrderById(id);
		if (reqest.getHeader("referer").contains("/userInfo")) {
			newPath = path.concat("/userInfo");
			model.addAttribute("ordersList", orderService.getAllOrdersForUser(usersService.getUserByLogin(login)));
			orderService.deleteOrder(order);
		} else if (reqest.getHeader("referer").contains("/all-orders")) {
			newPath = path.concat("/all-orders");
			model.addAttribute("ordersList", orderService.getAllOrders());
			orderService.deleteOrder(order);
		} else {
			newPath = path.concat("/");
		}
		return newPath;
	}
}
