package com.springdatarediscache.mapper;

import com.springdatarediscache.dto.CustomerDto;
import com.springdatarediscache.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CustomerMapper {

    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public abstract Customer customerDtoToCustomer(CustomerDto customerDto);

   public abstract CustomerDto customerToCustomerDto(Customer customer);

}
