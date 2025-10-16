package com.expensetracker.features.category;

import com.expensetracker.features.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(Users user);
    Optional<Category> getCategoryByIdAndUser(Long id, Users user);
    void removeCategoryByIdAndUser(Long id, Users user);
}
