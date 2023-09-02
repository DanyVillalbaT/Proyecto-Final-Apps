package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Venta;

public interface IVentaRepository extends JpaRepository<Venta, Long>{

}
