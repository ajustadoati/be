package com.ajustadoati.backend.adapter.rest.repository;

import com.ajustadoati.backend.domain.Category;
import com.ajustadoati.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
