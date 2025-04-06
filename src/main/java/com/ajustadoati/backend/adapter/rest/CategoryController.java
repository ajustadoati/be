package com.ajustadoati.backend.adapter.rest;

import com.ajustadoati.backend.adapter.rest.dto.CategoryDto;
import com.ajustadoati.backend.application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {
    return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCategory(Long id, CategoryDto categoryDto) {
    categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.noContent().build();
  }

}
