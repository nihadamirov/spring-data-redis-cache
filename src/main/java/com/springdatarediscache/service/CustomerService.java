package com.springdatarediscache.service;

import com.springdatarediscache.dto.CustomerDto;
import com.springdatarediscache.model.Customer;
import com.springdatarediscache.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

}
