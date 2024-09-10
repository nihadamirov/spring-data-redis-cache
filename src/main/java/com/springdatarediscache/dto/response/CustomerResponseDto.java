package com.springdatarediscache.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
}
