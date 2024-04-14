package com.politecnico.edu.inventario.repositories;

import com.politecnico.edu.inventario.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
