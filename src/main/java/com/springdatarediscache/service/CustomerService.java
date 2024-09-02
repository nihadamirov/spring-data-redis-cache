package com.springdatarediscache.service;

import com.springdatarediscache.dto.CustomerDto;
import com.springdatarediscache.exception.CustomerNotFoundException;
import com.springdatarediscache.mapper.CustomerMapper;
import com.springdatarediscache.model.Customer;
import com.springdatarediscache.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(updatedCustomer);
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        return customerMapper.customerToCustomerDto(customer);
    }

    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customerRepository.delete(customer);
    }

}
