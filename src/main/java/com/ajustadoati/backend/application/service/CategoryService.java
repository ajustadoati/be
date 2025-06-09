package com.ajustadoati.backend.application.service;

import com.ajustadoati.backend.adapter.rest.dto.CategoryDto;
import com.ajustadoati.backend.adapter.rest.mapper.CategoryMapper;
import com.ajustadoati.backend.adapter.rest.repository.CategoryRepository;
import com.ajustadoati.backend.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  private final CategoryMapper categoryMapper;

  public CategoryDto addCategory(CategoryDto categoryDto) {
    log.info("Adding new category: {}", categoryDto);
    Category category = categoryMapper.toEntity(categoryDto);

    return categoryMapper.toDto(categoryRepository.save(category));
  }

  public List<CategoryDto> getAllCategories() {
    log.info("Fetching all categories");
    List<Category> categories = categoryRepository.findAll();
    return categories.stream()
        .map(categoryMapper::toDto)
        .toList();
  }
  public void deleteCategory(Integer id) {
    log.info("Deleting category with id: {}", id);
    categoryRepository.deleteById(id);
  }

  public void updateCategory(Integer id, CategoryDto categoryDto) {
    log.info("Updating category with id: {}", id);
    var category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    category.setName(categoryDto.name());
    category.setDescription(categoryDto.description());
    category.setGooglePlaceType(categoryDto.googlePlaceType());
    categoryRepository.save(category);

  }
}
