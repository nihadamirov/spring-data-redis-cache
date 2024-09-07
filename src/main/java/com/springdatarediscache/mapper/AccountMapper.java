package com.springdatarediscache.mapper;

import com.springdatarediscache.dto.AccountDto;
import com.springdatarediscache.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    public abstract AccountDto accountToAccountDto(Account account);

    @Mapping(source = "customerId", target = "customer.id")
    public  abstract Account accountDtoToAccount(AccountDto accountDto);
}
