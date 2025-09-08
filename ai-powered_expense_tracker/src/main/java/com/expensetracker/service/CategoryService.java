package com.expensetracker.service;

import com.expensetracker.entity.Category;

public interface CategoryService {
    void addCategory(Category category);
    boolean removeCategoryById(Long id);
    Category getCategoryById(Long id);
    void printCategoryMap();
}
