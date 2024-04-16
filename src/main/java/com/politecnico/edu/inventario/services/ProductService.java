package com.politecnico.edu.inventario.services;

import com.politecnico.edu.inventario.models.Datos;
import com.politecnico.edu.inventario.models.Product;
import com.politecnico.edu.inventario.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void udpateStock(Datos datos){
        productRepository.findById(Long.valueOf(datos.getId()))
                .ifPresent(product -> {
                    product.setStock(datos.getStock());
                    productRepository.save(product);
                });
    }
}
