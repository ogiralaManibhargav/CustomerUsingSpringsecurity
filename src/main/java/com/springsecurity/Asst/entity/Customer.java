package com.springsecurity.Asst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name="Customer")
public class Customer {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private String street;

    private  String address;

    private String city;

    private String state;

    private  String email;

    private String phone;
}
