package com.springdatarediscache.controller;

import com.springdatarediscache.dto.request.CustomerRequestDto;
import com.springdatarediscache.dto.response.CustomerResponseDto;
import com.springdatarediscache.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerDto) {
        CustomerResponseDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerDto) {
        CustomerResponseDto updateCustomer = customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto getCustomer = customerService.getCustomerById(id);
        return new ResponseEntity<>(getCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer() {
        List<CustomerResponseDto> customerDto = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletedCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
