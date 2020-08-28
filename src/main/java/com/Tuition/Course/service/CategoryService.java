package com.Tuition.Course.service;

import com.Tuition.Course.model.Category;

import java.util.List;

public interface CategoryService {

  void addACategory(Category category);

  List<Category> getAllCategories();

  Category getACategory(long id);

  void updateACategory(long id,Category newCategory);

  void deleteACategory(long id);

}
