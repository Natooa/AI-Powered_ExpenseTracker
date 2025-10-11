package com.expensetracker.features.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategoryById(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " does not exist");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " does not exist");
        }
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category categoryToUpdate) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
        if(categoryToUpdate.getCategoryName() != null) {
            existing.setCategoryName(categoryToUpdate.getCategoryName());
        }
        if(categoryToUpdate.getCategoryDescription() != null) {
            existing.setCategoryDescription(categoryToUpdate.getCategoryDescription());
        }
        return categoryRepository.save(existing);
    }
}
