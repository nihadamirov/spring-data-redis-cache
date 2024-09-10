package com.springdatarediscache.service;

import com.springdatarediscache.dto.request.CustomerRequestDto;
import com.springdatarediscache.dto.response.CustomerResponseDto;
import com.springdatarediscache.exception.CustomerNotFoundException;
import com.springdatarediscache.mapper.CustomerMapper;
import com.springdatarediscache.model.Customer;
import com.springdatarediscache.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @CachePut(cacheNames = "customer", key = " 'createCustomer' + #id", unless = "#result == null")
    public CustomerResponseDto createCustomer(CustomerRequestDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.requestDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.INSTANCE.customerToResponseDto(savedCustomer);
    }

    @CachePut(cacheNames = "customer_id", key = " 'updateCustomer' + #id")
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToResponseDto(updatedCustomer);
    }

    @Cacheable(cacheNames = "customer_id", key = "'getCustomerById' + #id", unless = "#result == null")
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        return CustomerMapper.INSTANCE.customerToResponseDto(customer);
    }

    @Cacheable(value = "customer", key = "'getallCustomer'", unless = "#result == null")
    public List<CustomerResponseDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper.INSTANCE::customerToResponseDto)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"customer", "customer_id"}, allEntries = true)
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customerRepository.delete(customer);
    }
}
