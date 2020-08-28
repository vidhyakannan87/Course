package com.Tuition.Course.controller;

import com.Tuition.Course.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
public interface CategoryController {

  @PostMapping
  ResponseEntity createCategory(@RequestBody Category category);

  @GetMapping
  ResponseEntity getAllCategories();

  @GetMapping("/{id}")
  ResponseEntity getCategory(@PathVariable long id);

  @PutMapping("/{id}")
  ResponseEntity updateCategory(@PathVariable long id, @RequestBody Category category);

  @DeleteMapping("/{id}")
  ResponseEntity deleteCategory(@PathVariable long id);

}
