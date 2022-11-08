package com.example.bankapplication.dto.converter;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

public AccountDto converter(Account account){
    return AccountDto.builder()
            .id(account.getId())
            .balance(account.getBalance())
            .currency(account.getCurrency())
            .customerId(account.getCustomerId())
            .build();
}
}
