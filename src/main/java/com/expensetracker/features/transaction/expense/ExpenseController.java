package com.expensetracker.features.transaction.expense;

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
@RequestMapping("/expense")
public class ExpenseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    private final ExpenseServiceImpl expenseServiceImpl;
    private final CategoryRepository categoryRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseServiceImpl expenseServiceImpl, CategoryRepository categoryRepository,  ExpenseMapper expenseMapper) {
        this.expenseServiceImpl = expenseServiceImpl;
        this.categoryRepository = categoryRepository;
        this.expenseMapper = expenseMapper;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createExpense(@RequestBody TransactionDTO dto) {
        LOGGER.info("called createExpense");
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Expense expense = expenseMapper.expenseDTOToExpense(dto);
        expense.setCategory(category);
        Expense savedExpense = expenseServiceImpl.addTransaction(expense);
        TransactionDTO responseDTO = expenseMapper.expenseToExpenseDTO(savedExpense);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getExpenseById(
            @PathVariable Long id
    ) {
        LOGGER.info("called getExpenseById");

        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseMapper.expenseToExpenseDTO(expenseServiceImpl.getTransactionById(id)));

    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllExpenses() {
        LOGGER.info("called getAllExpenses");
        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseMapper.expenseToExpenseDTOList(expenseServiceImpl.getAllTransactions()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id
    ) {
        LOGGER.info("called DeleteExpense");

        expenseServiceImpl.removeTransactionById(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateExpense(
            @PathVariable Long id,
            @RequestBody TransactionDTO dto
    ) {
        LOGGER.info("called updateExpense");
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Expense expense =  expenseMapper.expenseDTOToExpense(dto);
        expense.setCategory(category);
        Expense savedExpense =  expenseServiceImpl.updateTransaction(id, expense);
        TransactionDTO responseDTO = expenseMapper.expenseToExpenseDTO(savedExpense);
        return ResponseEntity.ok().body(responseDTO);
    }
}
