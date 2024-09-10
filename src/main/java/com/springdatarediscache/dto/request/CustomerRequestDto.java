package com.springdatarediscache.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
}
