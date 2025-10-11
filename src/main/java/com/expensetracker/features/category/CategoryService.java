package com.expensetracker.features.category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void removeCategoryById(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
}
