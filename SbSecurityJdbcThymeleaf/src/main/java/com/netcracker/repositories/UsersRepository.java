package com.netcracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public Users findByUserName(String userName);
	public Users findByUserId(Long userId);
}
