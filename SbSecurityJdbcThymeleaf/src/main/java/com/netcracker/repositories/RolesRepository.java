package com.netcracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
