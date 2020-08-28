package com.Tuition.Course.controller.impl;

import com.Tuition.Course.controller.CategoryController;
import com.Tuition.Course.model.Category;
import com.Tuition.Course.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryControllerImpl implements CategoryController {

  private final CategoryService categoryService;

  public CategoryControllerImpl(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public ResponseEntity createCategory(@RequestBody Category category) {
    categoryService.addACategory(category);
    return ResponseUtility.buildOkResponse();
  }

  @Override
  public ResponseEntity getAllCategories() {

    return ResponseUtility.buildOkResponse( categoryService.getAllCategories());
  }

  @Override
  public ResponseEntity getCategory(@PathVariable long id) {
     Category category = categoryService.getACategory(id);
     return ResponseUtility.buildOkResponse(category);
  }

  @Override
  public ResponseEntity updateCategory(@PathVariable long id,@RequestBody Category category) {
    categoryService.updateACategory(id,category);
    return ResponseUtility.buildOkResponse();
  }

  @Override
  public ResponseEntity deleteCategory(@PathVariable long id) {
    categoryService.deleteACategory(id);
    return ResponseUtility.buildOkResponse();
  }
}
