package com.spring.accounts.service;

import com.spring.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    Boolean updateAccount(CustomerDto customerDto);

    Boolean deleteAccount(String mobileNumber);
}
