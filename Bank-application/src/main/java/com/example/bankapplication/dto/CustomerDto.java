package com.example.bankapplication.dto;

import com.example.bankapplication.model.City;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private String address;

}

