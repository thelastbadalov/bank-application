package com.example.bankapplication.dto.converter;

import com.example.bankapplication.dto.CityDto;
import com.example.bankapplication.dto.CustomerDto;
import com.example.bankapplication.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

public CustomerDto converter(Customer customer){


return      CustomerDto.builder()
            .id(customer.getId())
            .address(customer.getAddress())
            .city(CityDto.valueOf(customer.getCity().name()))
            .name(customer.getName())
            .dateOfBirth(customer.getDateOfBirth())
            .build();
}
}
