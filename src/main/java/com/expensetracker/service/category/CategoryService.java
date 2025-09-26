package com.expensetracker.service.category;

import com.expensetracker.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void removeCategoryById(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
}
