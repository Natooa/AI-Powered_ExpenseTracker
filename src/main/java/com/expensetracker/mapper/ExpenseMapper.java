package com.expensetracker.mapper;

import com.expensetracker.dto.TransactionDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpenseMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "notes", target = "notes")
    Expense expenseDTOToExpense(TransactionDTO transactionDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "category.id", target = "categoryId")
    TransactionDTO expenseToExpenseDTO(Expense expense);

    List<TransactionDTO> expenseToExpenseDTOList(List<Expense> expenses);
}
