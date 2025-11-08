package com.expensetracker.features.transaction.expense;

import com.expensetracker.features.transaction.base.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Expense expenseDTOToExpense(TransactionDTO transactionDTO);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    TransactionDTO expenseToExpenseDTO(Expense expense);

    List<TransactionDTO> expenseToExpenseDTOList(List<Expense> expenses);
}
