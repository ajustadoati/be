package com.ajustadoati.backend.adapter.rest.repository;

import com.ajustadoati.backend.domain.SearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchRequest, Integer> {
}
