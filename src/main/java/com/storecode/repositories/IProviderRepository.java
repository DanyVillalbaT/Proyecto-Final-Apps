package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Provider;

public interface IProviderRepository extends JpaRepository<Provider, Integer>{

}
