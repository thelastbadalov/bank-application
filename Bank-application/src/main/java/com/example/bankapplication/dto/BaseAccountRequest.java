package com.example.bankapplication.dto;

import com.example.bankapplication.model.City;
import com.example.bankapplication.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseAccountRequest {
    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
