package com.ajustadoati.backend.adapter.rest.repository;

import com.ajustadoati.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
