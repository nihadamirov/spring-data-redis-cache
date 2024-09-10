package com.springdatarediscache.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDto {

    private BigDecimal balance;
}
