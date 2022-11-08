package com.example.bankapplication.service;

import com.example.bankapplication.dto.CreateCustomerRequest;
import com.example.bankapplication.dto.CustomerDto;
import com.example.bankapplication.dto.UpdateCustomerRequest;
import com.example.bankapplication.dto.converter.CustomerDtoConverter;
import com.example.bankapplication.exception.CustomerNotFoundException;
import com.example.bankapplication.model.City;
import com.example.bankapplication.model.Customer;
import com.example.bankapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){

    Customer customer=new Customer();
    customer.setId(createCustomerRequest.getId());
    customer.setAddress(createCustomerRequest.getAddress());
customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));
customer.setName(createCustomerRequest.getName());
customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());

   customerRepository.save(customer);

   return converter.converter(customer);
    }

   public List<CustomerDto> getAllCustomers(){
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDto> customerDtos=new ArrayList<>();
        for (Customer customer:customers){
            customerDtos.add(converter.converter(customer));
        }
return customerDtos;
   }


public CustomerDto getCustomerDtoById(String id){
return customerRepository.findById(id).map(converter::converter).
        orElseThrow(()->new CustomerNotFoundException("can not find customer with given id"+id));

    }
public void deleteCustomerById(String id){
         customerRepository.deleteById(id);
}

    public CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest) {
Optional<Customer> customerOptional= customerRepository.findById(id);

    customerOptional.ifPresent(customer ->{
        customer.setName(updateCustomerRequest.getName());
        customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
        customer.setAddress(updateCustomerRequest.getAddress());
customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
customerRepository.save(customer);
    });

    return customerOptional.map(converter::converter).orElseThrow(()->
            new CustomerNotFoundException("can not find customer with given id"+id));
    }

    protected Customer getCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException("can not find customer with given id"));
    }
}
