package com.devsu.customer.adapter.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerViewModel {

    private String id;
    private String name;
    private String gender;
    private Integer age;
    private Boolean state;
    private String password;

}
