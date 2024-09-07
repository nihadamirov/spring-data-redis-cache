package com.springdatarediscache.service;

import com.springdatarediscache.dto.CustomerDto;
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

    @CacheEvict(value = {"customer", "customer_id"}, allEntries = true)
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.INSTANCE.customerToCustomerDto(savedCustomer);
    }

    @CachePut(cacheNames= "customer_id", key = " 'updateCustomer' + #customerDto.id", unless = "#result == null ")
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(updatedCustomer);
    }

    @Cacheable(cacheNames = "customer_id", key = "#root.methodName + #id", unless = "#result == null")
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @Cacheable(value = "customer", key = "#root.methodName", unless = "#result == null")
    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"customer", "customer_id"}, allEntries = true)
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id " + id + "not found"));

        customerRepository.delete(customer);
    }

}
