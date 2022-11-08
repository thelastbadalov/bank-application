package com.example.bankapplication.dto;

import com.example.bankapplication.model.City;
import com.example.bankapplication.model.Currency;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;
}
