package com.springdatarediscache.mapper;

import com.springdatarediscache.dto.request.AccountRequestDto;
import com.springdatarediscache.dto.response.AccountResponseDto;
import com.springdatarediscache.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    public abstract AccountResponseDto accountToResponseDto(Account account);
    public abstract Account requestDtoToAccount(AccountRequestDto accountRequestDto);
}
