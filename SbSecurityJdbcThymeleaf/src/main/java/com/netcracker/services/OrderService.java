package com.netcracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Order;
import com.netcracker.model.Users;
import com.netcracker.repositories.OrderRepository;
import com.netcracker.services.interfaces.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrdersForUser(Users user) {
		return orderRepository.findByUser(user);
	}

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.saveAndFlush(order);
	}
}
