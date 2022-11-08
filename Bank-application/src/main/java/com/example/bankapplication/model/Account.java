package com.example.bankapplication.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
private String id;
private String customerId;
private Double balance;
private City city;
private Currency currency;


}
