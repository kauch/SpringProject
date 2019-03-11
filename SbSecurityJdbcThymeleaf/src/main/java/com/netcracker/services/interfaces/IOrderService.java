package com.netcracker.services.interfaces;

import java.util.List;

import com.netcracker.model.Order;
import com.netcracker.model.Users;

public interface IOrderService {

	public List<Order> getAllOrdersForUser(Users user);

	public Order saveOrder(Order order);

	public List<Order> getAllOrders();
}
