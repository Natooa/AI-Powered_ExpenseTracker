package com.expensetracker.controller;

import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private static final Logger LOGGER = Logger.getLogger(ExpenseController.class.getName());

    private final ExpenseServiceImpl expenseServiceImpl;

    public ExpenseController(ExpenseServiceImpl expenseServiceImpl) {
        this.expenseServiceImpl = expenseServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        LOGGER.info("called createExpense");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseServiceImpl.addTransaction(expense));
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
    public ResponseEntity<Map<Long, Expense>>  getAllExpenses() {
        LOGGER.info("called getAllExpenses");
        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseServiceImpl.getTransactionMap());
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
