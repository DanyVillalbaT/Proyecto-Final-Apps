package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.storecode.models.Categoria;


public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
