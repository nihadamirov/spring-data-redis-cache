package com.springdatarediscache.mapper;

import com.springdatarediscache.dto.request.CustomerRequestDto;
import com.springdatarediscache.dto.response.CustomerResponseDto;
import com.springdatarediscache.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CustomerMapper {

    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public abstract Customer requestDtoToCustomer(CustomerRequestDto customerRequestDto);
   public abstract CustomerResponseDto customerToResponseDto(Customer customer);

}
