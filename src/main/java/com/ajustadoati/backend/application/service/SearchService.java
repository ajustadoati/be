package com.ajustadoati.backend.application.service;

import com.ajustadoati.backend.adapter.rest.dto.SearchDto;
import com.ajustadoati.backend.adapter.rest.mapper.SearchMapper;
import com.ajustadoati.backend.adapter.rest.repository.CategoryRepository;
import com.ajustadoati.backend.adapter.rest.repository.SearchRepository;
import com.ajustadoati.backend.adapter.rest.repository.UserRepository;
import com.ajustadoati.backend.domain.SearchRequest;
import com.ajustadoati.backend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final SearchMapper searchMapper;

    public SearchDto save(SearchDto dto){
        var category = categoryRepository.findById(dto.categoryId()).orElseThrow();

        User user = null;
        if (dto.userId() != null ){
            user = userRepository.findById(dto.userId()).orElse(null);
        }

        SearchRequest request = SearchRequest.builder()
            .query(dto.query())
            .category(category)
            .latitude(dto.latitude())
            .longitude(dto.longitude())
            .createdAt(Instant.now())
            .user(user)
            .build();

        return searchMapper.toDto(searchRepository.save(request));
    }

    public List<SearchDto> getAll(){

        return searchRepository.findAll().stream().map(searchMapper::toDto).toList();
    }

}
