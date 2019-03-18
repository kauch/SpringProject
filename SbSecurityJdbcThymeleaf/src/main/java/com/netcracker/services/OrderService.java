package com.netcracker.services;

import java.util.List;

import com.netcracker.model.Order;
import com.netcracker.model.Users;

public interface OrderService {

	public List<Order> getAllOrdersForUser(Users user);

	public Order saveOrder(Order order);

	public List<Order> getAllOrders();
}
