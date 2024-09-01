package com.springdatarediscache.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

}
