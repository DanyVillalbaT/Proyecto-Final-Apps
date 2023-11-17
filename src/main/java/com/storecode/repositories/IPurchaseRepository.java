package com.storecode.repositories;

import com.storecode.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Purchase;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{

    List<Purchase> findByUser(User user);

}
