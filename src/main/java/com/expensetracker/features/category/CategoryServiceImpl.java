package com.expensetracker.features.category;

import com.expensetracker.features.users.Users;
import com.expensetracker.features.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UsersRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,  UsersRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Category addCategory(Category category) {
        Users currentUser = getCurrentUser();
        category.setUser(currentUser);
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategoryById(Long id) {
        Users currentUser = getCurrentUser();
        if(!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " does not exist");
        }
        categoryRepository.removeCategoryByIdAndUser(id, currentUser);
    }

    @Override
    public Category getCategoryById(Long id) {
        Users currentUser = getCurrentUser();
        return categoryRepository.getCategoryByIdAndUser(id, currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllByUser(getCurrentUser());
    }

    @Override
    public Category updateCategory(Long id, Category categoryToUpdate) {
        Users currentUser = getCurrentUser();
        Category existing = categoryRepository.getCategoryByIdAndUser(id, currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
        if(categoryToUpdate.getCategoryName() != null) {
            existing.setCategoryName(categoryToUpdate.getCategoryName());
        }
        if(categoryToUpdate.getCategoryDescription() != null) {
            existing.setCategoryDescription(categoryToUpdate.getCategoryDescription());
        }
        return categoryRepository.save(existing);
    }

    private Users getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Username " + username + " not found"));
    }
}
