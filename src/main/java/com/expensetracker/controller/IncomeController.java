package com.expensetracker.controller;

import com.expensetracker.entity.Income;
import com.expensetracker.service.IncomeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/income")
public class IncomeController {
    private static final Logger LOGGER = Logger.getLogger(IncomeController.class.getName());

    private final IncomeServiceImpl incomeServiceImpl;

    public IncomeController(IncomeServiceImpl incomeServiceImpl) {
        this.incomeServiceImpl = incomeServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        LOGGER.info("called createIncome");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incomeServiceImpl.addTransaction(income));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getIncomeById");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(incomeServiceImpl.getTransactionById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<Map<Long, Income>> getAllIncome() {
        LOGGER.info("called geAllIncome");
        return ResponseEntity.ok(incomeServiceImpl.getTransactionMap());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable("id") Long id
    ){
        LOGGER.info("called deleteIncome");
        try{
            incomeServiceImpl.removeTransactionById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(
            @PathVariable("id") Long id,
            @RequestBody Income income
    ) {
        LOGGER.info("called updateIncome");
        var update = incomeServiceImpl.updateTransaction(id, income);
        return ResponseEntity.ok(update);
    }
}
