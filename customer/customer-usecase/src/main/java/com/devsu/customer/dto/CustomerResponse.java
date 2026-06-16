package com.devsu.customer.dto;

import java.util.UUID;
import com.devsu.customer.domain.model.Customer;
import lombok.Data;

@Data
public class CustomerResponse {
    private UUID id;
    private String name;
    private String gender;
    private Integer age;
    private Boolean state;

    public static CustomerResponse fromDomain(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getPerson().getName());
        response.setGender(customer.getPerson().getGender().name());
        response.setAge(customer.getPerson().getAge());
        response.setState(customer.getState());
        return response;
    }
}
