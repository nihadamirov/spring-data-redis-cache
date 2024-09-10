package com.springdatarediscache.service;

import com.springdatarediscache.dto.request.AccountRequestDto;
import com.springdatarediscache.dto.response.AccountResponseDto;
import com.springdatarediscache.exception.AccountNotFoundException;
import com.springdatarediscache.exception.CustomerNotFoundException;
import com.springdatarediscache.mapper.AccountMapper;
import com.springdatarediscache.model.Account;
import com.springdatarediscache.model.Customer;
import com.springdatarediscache.repository.AccountRepository;
import com.springdatarediscache.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    private final SecureRandom random = new SecureRandom();
    private final int ACCOUNT_NUMBER_LENGTH = 16;

    public AccountResponseDto getAccountByAccountNumber(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with account number " + accountNumber));

        return AccountMapper.INSTANCE.accountToResponseDto(account);
    }

    public AccountResponseDto createAccount(Long customerId, AccountRequestDto accountDto) {
        Account account = AccountMapper.INSTANCE.requestDtoToAccount(accountDto);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + customerId + "not found"));

        account.setCustomer(customer);

        account.setAccountNumber(generateRandomAccountNumber());
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.INSTANCE.accountToResponseDto(savedAccount);
    }

    public String generateRandomAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder(16);
        accountNumber.append('4');

        for (int i = 1; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
