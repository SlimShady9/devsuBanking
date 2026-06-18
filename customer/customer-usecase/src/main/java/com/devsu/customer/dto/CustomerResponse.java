package com.devsu.customer.dto;

import java.util.UUID;
import com.devsu.customer.domain.model.Customer;
import lombok.Data;

@Data
public class CustomerResponse {
    private UUID customerId;
    private String name;
    private String gender;
    private Integer age;
    private Boolean state;
    private String password;

    public static CustomerResponse fromDomain(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setCustomerId(customer.getId());
        response.setName(customer.getPerson().getName());
        response.setGender(customer.getPerson().getGender().name());
        response.setAge(customer.getPerson().getAge());
        response.setState(customer.getState());
        response.setPassword(customer.getPassword());
        return response;
    }
}
