package com.netcracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.model.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

}
