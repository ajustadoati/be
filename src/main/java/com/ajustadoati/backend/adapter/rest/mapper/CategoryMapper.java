package com.ajustadoati.backend.adapter.rest.mapper;

import com.ajustadoati.backend.adapter.rest.dto.CategoryDto;
import com.ajustadoati.backend.domain.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDto toDto(Category category);
  Category toEntity(CategoryDto categoryDto);
}
