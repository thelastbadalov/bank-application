package com.example.bankapplication;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.City;
import com.example.bankapplication.model.Currency;
import com.example.bankapplication.model.Customer;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.CustomerRepository;
import org.apache.tomcat.jni.Address;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {
private final AccountRepository accountRepository;
private final CustomerRepository customerRepository;

	public BankApplication(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			Customer c1 = Customer.builder()
					.id("1234568")
					.name("Qulu")
					.city(City.LANKARAN)
					.dateOfBirth(2003)
					.address("28 may avenue")
					.build();


			Customer c2 = Customer.builder()
					.id("789456")
					.name("Resad")
					.city(City.SHAMAKHI)
					.dateOfBirth(2000)
					.address("sumgait JalilMammadGuluzade avenue")
					.build();

			Customer c3 = Customer.builder()
					.id("456238")
					.name("Sahin")
					.city(City.LANKARAN)
					.dateOfBirth(2005)
					.address("Elmler")
					.build();

		customerRepository.saveAll(Arrays.asList(c1,c2,c3));

			Account a1 = Account.builder()
					.id("100")
					.customerId("1234568")
					.city(City.GANDJA)
					.balance(1320.0)
					.currency(Currency.AZN)
					.build();
			Account a2 = Account.builder()
					.id("101")
					.customerId("789456")
					.city(City.ISTANBUL)
					.balance(7898.0)
					.build();
			Account a3 = Account.builder()
					.id("102")
					.customerId("456238")
					.city(City.BAKU)
					.balance(120000.0)
					.currency(Currency.AZN)
					.build();
			accountRepository.saveAll(Arrays.asList(a1,a2,a3));


	}
}
