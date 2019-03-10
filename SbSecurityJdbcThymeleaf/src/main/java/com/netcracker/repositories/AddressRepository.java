package com.netcracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
