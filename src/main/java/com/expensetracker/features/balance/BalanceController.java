package com.expensetracker.features.balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceController.class);

    private final BalanceServiceImpl balanceService;

    public BalanceController(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/totalBalance")
    public ResponseEntity<BigDecimal>  getBalance() {
        LOGGER.info("getBalance()");
        return ResponseEntity.ok(balanceService.getBalance());
    }

    @GetMapping("/totalIncome")
    public ResponseEntity<BigDecimal>  getTotalIncome() {
        LOGGER.info("getTotalIncome()");
        return ResponseEntity.ok(balanceService.getTotalIncome());
    }

    @GetMapping("/totalExpense")
    public ResponseEntity<BigDecimal>  getTotalExpense() {
        LOGGER.info("getTotalExpense()");
        return ResponseEntity.ok(balanceService.getTotalExpense());
    }

    @GetMapping
    public ResponseEntity<BalanceDTO>  getBalanceDTO() {
        LOGGER.info("getBalanceDTO()");
        return ResponseEntity.ok(balanceService.getBalanceDTO());
    }
}
