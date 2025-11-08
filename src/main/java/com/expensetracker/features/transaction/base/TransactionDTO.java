package com.expensetracker.features.transaction.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String name;
    private BigDecimal amount;
    private Long categoryId;
    private String notes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
