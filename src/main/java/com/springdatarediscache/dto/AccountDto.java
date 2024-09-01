package com.springdatarediscache.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {

    private String accountNumber;
    private BigDecimal balance;

}
