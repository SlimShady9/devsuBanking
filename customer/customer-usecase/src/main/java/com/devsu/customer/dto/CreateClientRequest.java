package com.devsu.customer.dto;

import lombok.Data;

@Data
public class CreateClientRequest {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean state;
}
