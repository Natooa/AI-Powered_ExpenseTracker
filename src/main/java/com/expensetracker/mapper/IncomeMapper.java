package com.expensetracker.mapper;

import com.expensetracker.dto.TransactionDTO;
import com.expensetracker.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncomeMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "notes", target = "notes")
    Income incomeDTOToIncome(TransactionDTO transactionDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "category.id", target = "categoryId")
    TransactionDTO incomeToIncomeDTO(Income income);

    List<TransactionDTO> incomeToIncomeDTOList(List<Income> incomes);
}
