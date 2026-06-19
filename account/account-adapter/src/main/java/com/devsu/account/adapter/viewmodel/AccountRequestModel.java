package com.devsu.account.adapter.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountRequestModel {

    private String customerId;
    private String accountType;
    private Boolean state;

}