package com.expensetracker.controller.transaction;

import com.expensetracker.dto.TransactionDTO;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.service.transaction.IncomeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/income")
public class IncomeController {
    private static final Logger LOGGER = Logger.getLogger(IncomeController.class.getName());

    private final IncomeServiceImpl incomeServiceImpl;
    private final CategoryRepository categoryRepository;

    public IncomeController(IncomeServiceImpl incomeServiceImpl, CategoryRepository categoryRepository) {
        this.incomeServiceImpl = incomeServiceImpl;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody TransactionDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found"));
        LOGGER.info("called createIncome");
        Income income = new Income(dto.getName(), dto.getAmount(), category,  dto.getNotes());
        Income savedIncome = incomeServiceImpl.addTransaction(income);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedIncome);
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
    public ResponseEntity<List<Income>> getAllIncome() {
        LOGGER.info("called geAllIncome");
        return ResponseEntity.ok(incomeServiceImpl.getAllTransactions());
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
