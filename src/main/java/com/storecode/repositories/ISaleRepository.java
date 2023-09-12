package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Sale;

public interface ISaleRepository extends JpaRepository<Sale, Long>{

}
