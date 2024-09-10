package com.springdatarediscache.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Account> account;
}
