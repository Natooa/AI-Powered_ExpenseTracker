package com.expensetracker.features.transaction.income;

import com.expensetracker.features.transaction.base.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomeMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Income incomeDTOToIncome(TransactionDTO transactionDTO);

    @Mapping(source = "category.id", target = "categoryId")
    TransactionDTO incomeToIncomeDTO(Income income);

    List<TransactionDTO> incomeToIncomeDTOList(List<Income> incomes);
}
