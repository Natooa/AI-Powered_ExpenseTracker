package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.service.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(
            @RequestBody Category categoryToAdd
    ) {
        LOGGER.info("called addCategory");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryServiceImpl.addCategory(categoryToAdd));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getCategoryById");
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoryServiceImpl.getCategoryById(id));
        } catch(NoSuchElementException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<Map<Long, Category>> getAllCategories() {
        LOGGER.info("called getAllCategories");
        return ResponseEntity.ok(categoryServiceImpl.getCategoryMap());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(
            @PathVariable Long id
    ){
        LOGGER.info("called deleteCategoryById");
        try{
            categoryServiceImpl.removeCategoryById(id);
            return ResponseEntity.ok().build();
        } catch(NoSuchElementException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category categoryToUpdate
    ) {
        LOGGER.info("called updateCategory");
        var updatedCategory = categoryServiceImpl.updateCategory(id, categoryToUpdate);
        return ResponseEntity.ok(updatedCategory);
    }
}
