package com.springdatarediscache.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Account> account;
}
