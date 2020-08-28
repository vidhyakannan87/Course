package com.Tuition.Course.service.impl;

import com.Tuition.Course.model.Category;
import com.Tuition.Course.repository.CategoryRepository;
import com.Tuition.Course.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final String CATEGORY_NOT_FOUND = "Category Not Found";

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  @Override
  public void addACategory(Category category) {
    saveCategory(category);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category getACategory(long id) {
    Optional<Category> category = categoryRepository.findById(id);
    if(!category.isPresent()){
      throw  new EntityNotFoundException(CATEGORY_NOT_FOUND);
    }

    return category.get();
  }

  @Override
  public void updateACategory(long id, Category newCategory) {
    getACategory(id);
    newCategory.setId(id);
    saveCategory(newCategory);
  }

  @Override
  public void deleteACategory(long id) {

    Category category = getACategory(id);
    categoryRepository.delete(category);

  }


  public void saveCategory(Category category){
    categoryRepository.saveAndFlush(category);
  }
}
