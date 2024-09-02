package com.springdatarediscache.service;

import com.springdatarediscache.dto.AccountDto;
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
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    private final SecureRandom random = new SecureRandom();
    private final int ACCOUNT_NUMBER_LENGTH = 16;

    public AccountDto getAccountByAccountNumber(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with account number " + accountNumber));

        return accountMapper.accountToAccountDto(account);
    }

    public AccountDto createAccount(Long customerId, AccountDto accountDto) {
        Account account = accountMapper.accountDtoToAccount(accountDto);

        Customer  customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + customerId + "not found"));

        account.setCustomer(customer);

        account.setAccountNumber(generateRandomAccountNumber());
        Account savedAccount = accountRepository.save(account);

        return accountMapper.accountToAccountDto(savedAccount);
    }

    public String generateRandomAccountNumber() {

        //generating 16 random numbers between 0 - 9
        return random.ints(ACCOUNT_NUMBER_LENGTH, 0, 16)
                .mapToObj(String::valueOf)
                .reduce(String::concat)
                .orElseThrow();
    }
}
