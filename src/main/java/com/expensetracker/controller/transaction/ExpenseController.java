package com.expensetracker.controller.transaction;

import com.expensetracker.dto.TransactionDTO;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.service.transaction.ExpenseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private static final Logger LOGGER = Logger.getLogger(ExpenseController.class.getName());

    private final ExpenseServiceImpl expenseServiceImpl;
    private final CategoryRepository categoryRepository;

    public ExpenseController(ExpenseServiceImpl expenseServiceImpl, CategoryRepository categoryRepository) {
        this.expenseServiceImpl = expenseServiceImpl;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody TransactionDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found"));
        Expense expense = new Expense(dto.getName(), dto.getAmount(), category, dto.getNotes());
        Expense savedExpense = expenseServiceImpl.addTransaction(expense);
        LOGGER.info("called createExpense");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedExpense);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getExpenseById");
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(expenseServiceImpl.getTransactionById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Expense>>  getAllExpenses() {
        LOGGER.info("called getAllExpenses");
        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseServiceImpl.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteExpense(
            @PathVariable Long id
    ) {
        LOGGER.info("called DeleteExpense");
        try{
            expenseServiceImpl.removeTransactionById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense
    ) {
        LOGGER.info("called updateExpense");
        var update =  expenseServiceImpl.updateTransaction(id, expense);
        return ResponseEntity.ok().body(update);
    }
}
