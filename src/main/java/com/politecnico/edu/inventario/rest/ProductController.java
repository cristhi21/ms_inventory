package com.politecnico.edu.inventario.rest;

import com.politecnico.edu.inventario.models.Product;
import com.politecnico.edu.inventario.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findByName(@PathVariable Long id) {
        return productService.findById(id);
    }
}
