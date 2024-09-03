package com.springdatarediscache.controller;

import com.springdatarediscache.dto.AccountDto;
import com.springdatarediscache.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable Long customerId, @RequestBody AccountDto accountDto) {
        AccountDto createAccount = accountService.createAccount(customerId, accountDto);

        return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountDto> getAccountByAccountNumber(@RequestBody String accountNumber) {
        AccountDto accountDto = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);

    }

}
