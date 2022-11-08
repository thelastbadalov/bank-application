package com.example.bankapplication.dto;

import com.example.bankapplication.model.City;
import lombok.*;
import org.hibernate.annotations.NaturalIdCache;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerRequest {
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private String address;
}
