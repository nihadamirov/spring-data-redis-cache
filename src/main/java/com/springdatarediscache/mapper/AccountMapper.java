package com.springdatarediscache.mapper;

import com.springdatarediscache.dto.AccountDto;
import com.springdatarediscache.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "customerId", target = "customer.id")
    Account accountDtoToAccount(AccountDto accountDto);
}
