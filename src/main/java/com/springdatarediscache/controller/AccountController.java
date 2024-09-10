package com.springdatarediscache.controller;

import com.springdatarediscache.dto.request.AccountRequestDto;
import com.springdatarediscache.dto.response.AccountResponseDto;
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
    public ResponseEntity<AccountResponseDto> createAccount(@PathVariable Long customerId, @RequestBody AccountRequestDto accountDto) {
        AccountResponseDto createAccount = accountService.createAccount(customerId, accountDto);

        return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountResponseDto> getAccountByAccountNumber(@RequestParam String accountNumber) {
        AccountResponseDto accountDto = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);

    }
}
