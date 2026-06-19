package com.devsu.customer.adapter.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerRequestModel {

    private String name;
    private String gender;
    private Integer age;
    private Boolean state;
    private String password;

}