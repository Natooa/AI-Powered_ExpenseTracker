package com.expensetracker.features.transaction.income;

import com.expensetracker.features.transaction.base.TransactionDTO;
import com.expensetracker.features.category.Category;
import com.expensetracker.features.category.CategoryRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;

@RestController
@RequestMapping("/income")
public class IncomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IncomeController.class);

    private final IncomeServiceImpl incomeServiceImpl;
    private final CategoryRepository categoryRepository;
    private final IncomeMapper incomeMapper;

    public IncomeController(IncomeServiceImpl incomeServiceImpl, CategoryRepository categoryRepository,  IncomeMapper incomeMapper) {
        this.incomeServiceImpl = incomeServiceImpl;
        this.categoryRepository = categoryRepository;
        this.incomeMapper = incomeMapper;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createIncome(@RequestBody TransactionDTO dto) {
        LOGGER.info("called createIncome");

        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Income income = (Income) incomeMapper.incomeDTOToIncome(dto);
        income.setCategory(category);
        Income savedIncome = incomeServiceImpl.addTransaction(income);
        TransactionDTO responseDto = incomeMapper.incomeToIncomeDTO(savedIncome);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getIncomeById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getIncomeById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(incomeMapper.incomeToIncomeDTO(incomeServiceImpl.getTransactionById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllIncome() {
        LOGGER.info("called geAllIncome");
        return ResponseEntity.ok(incomeMapper.incomeToIncomeDTOList(incomeServiceImpl.getAllTransactions()));
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
    public ResponseEntity<TransactionDTO> updateIncome(
            @PathVariable("id") Long id,
            @RequestBody TransactionDTO dto
    ) {
        LOGGER.info("called updateIncome");
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Income income = (Income) incomeMapper.incomeDTOToIncome(dto);
        income.setCategory(category);
        Income savedIncome = incomeServiceImpl.updateTransaction(id, income);
        TransactionDTO responseDto = incomeMapper.incomeToIncomeDTO(savedIncome);
        return ResponseEntity.ok(responseDto);
    }
}
