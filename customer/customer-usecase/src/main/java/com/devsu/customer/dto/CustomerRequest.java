package com.devsu.customer.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CustomerRequest {

    private UUID customerId;
    private UUID personId;
    private String name;
    private String gender;
    private Integer age;
    private String password;
    private Boolean state;
}
