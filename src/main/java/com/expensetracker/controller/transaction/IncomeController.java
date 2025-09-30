package com.expensetracker.controller.transaction;

import com.expensetracker.dto.TransactionDTO;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.service.transaction.IncomeServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.slf4j.Logger;

@RestController
@RequestMapping("/income")
public class IncomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IncomeController.class);

    private final IncomeServiceImpl incomeServiceImpl;
    private final CategoryRepository categoryRepository;

    public IncomeController(IncomeServiceImpl incomeServiceImpl, CategoryRepository categoryRepository) {
        this.incomeServiceImpl = incomeServiceImpl;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody TransactionDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        LOGGER.info("called createIncome");
        Income income = new Income(dto.getName(), dto.getAmount(), category, dto.getNotes());
        Income savedIncome = incomeServiceImpl.addTransaction(income);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedIncome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getIncomeById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(incomeServiceImpl.getTransactionById(id));
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAllIncome() {
        LOGGER.info("called geAllIncome");
        return ResponseEntity.ok(incomeServiceImpl.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable("id") Long id
    ) {
        LOGGER.info("called deleteIncome");
        incomeServiceImpl.removeTransactionById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(
            @PathVariable("id") Long id,
            @RequestBody TransactionDTO dto
    ) {
        LOGGER.info("called updateIncome");
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Income income = new Income(dto.getName(), dto.getAmount(), category, dto.getNotes());
        var update = incomeServiceImpl.updateTransaction(id, income);
        return ResponseEntity.ok(update);
    }
}
