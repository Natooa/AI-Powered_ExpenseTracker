package com.expensetracker.service;

import com.expensetracker.entity.Category;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final HashMap<Long, Category> categoryMap = new HashMap<>();

    @Override
    public Category addCategory(Category category) {
        categoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public void removeCategoryById(Long id) {
        if(!categoryMap.containsKey(id)) {
            throw new NoSuchElementException("Category with id " + id + " does not exist");
        }
        categoryMap.remove(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        if(!categoryMap.containsKey(id)) {
            throw new NoSuchElementException("Category with id " + id + " does not exist");
        }
        return categoryMap.get(id);
    }

    @Override
    public Map<Long, Category> getCategoryMap() {
        return Map.copyOf(categoryMap);
    }

    @Override
    public Category updateCategory(Long id, Category categoryToUpdate) {
        if(!categoryMap.containsKey(id)) {
            throw new NoSuchElementException("Category with id " + categoryToUpdate.getId() + " does not exist");
        }

        var category =  categoryMap.get(id);
        if(categoryToUpdate.getCategoryName() != null) {
            category.setCategoryName(categoryToUpdate.getCategoryName());
        }
        if(categoryToUpdate.getCategoryDescription() != null) {
            category.setCategoryDescription(categoryToUpdate.getCategoryDescription());
        }
        return category;
    }
}
