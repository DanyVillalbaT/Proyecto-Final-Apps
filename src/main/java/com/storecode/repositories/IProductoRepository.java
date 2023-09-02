package com.storecode.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long>{

}
