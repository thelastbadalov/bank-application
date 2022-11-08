package com.example.bankapplication.model;

import com.fasterxml.jackson.core.Base64Variant;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
private String id;
private String name;
private Integer dateOfBirth;
private City city;
private String address;

}
