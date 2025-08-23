package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;

import java.util.HashMap;

public class CategoryService {
    private final HashMap<Long, Category> categoryMap = new HashMap<>();

    public HashMap<Long, Category> getCategoryMap() {
        return categoryMap;
    }

    public void printCategoryMap() {
        categoryMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " = " +  entry.getValue());
        });
    }

    public Category getCategoryById(long id) {
        return categoryMap.get(id);
    }

    public void addCategory(Category category) {
        categoryMap.put(category.getId(), category);
    }

    public void removeCategoryById(Long id) {
        if(categoryMap.containsKey(id)) {
            categoryMap.remove(id);
        } else {
            System.out.println("There is no such category with id " + id);
        }
    }
}
