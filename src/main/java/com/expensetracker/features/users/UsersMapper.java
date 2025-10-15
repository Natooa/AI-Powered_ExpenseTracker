package com.expensetracker.features.users;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper{

    Users usersDTOToUsers(UsersDTO usersDTO);

    UsersDTO usersToUsersDTO(Users users);

}
