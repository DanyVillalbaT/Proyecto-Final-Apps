package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Purchase;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{

}
