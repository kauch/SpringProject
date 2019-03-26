package com.netcracker.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.model.Order;
import com.netcracker.model.Users;
import com.netcracker.repositories.OrderRepository;
import com.netcracker.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

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

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long orderId) {
		return orderRepository.findByOrderId(orderId);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
}
