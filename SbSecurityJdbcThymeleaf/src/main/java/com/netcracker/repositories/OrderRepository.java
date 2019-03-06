package com.netcracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Order;
import com.netcracker.model.Users;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findByUser(Users user);
}
