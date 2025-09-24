package com.expensetracker.service;

import com.expensetracker.entity.Category;

import java.util.Map;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void removeCategoryById(Long id);
    Category getCategoryById(Long id);
    Map<Long, Category> getCategoryMap();
}
