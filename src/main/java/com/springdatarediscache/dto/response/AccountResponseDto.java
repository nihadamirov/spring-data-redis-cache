package com.springdatarediscache.dto.response;

import com.springdatarediscache.dto.request.CustomerRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private CustomerRequestDto customer;
}
