package com.ajustadoati.backend.adapter.rest.repository;


import com.ajustadoati.backend.domain.Category;
import com.ajustadoati.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByNumberId(String numberId);
  List<User> findByCategories(List<Category> categories);

}
