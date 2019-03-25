package com.netcracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public Users findByLogin(String login);

	public Users findByUserId(Long userId);
}
