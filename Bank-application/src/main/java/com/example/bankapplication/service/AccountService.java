package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.CreateAccountRequest;
import com.example.bankapplication.dto.UpdateAccountRequest;
import com.example.bankapplication.dto.converter.AccountDtoConverter;
import com.example.bankapplication.exception.CustomerNotFoundException;
import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.Customer;
import com.example.bankapplication.repository.AccountRepository;
import org.springframework.data.util.ParsingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest accountRequest) {
        Customer customer = customerService.getCustomerById(accountRequest.getCustomerId());
        if (customer.getId().equals("") || customer.getId() == null) {
            return AccountDto.builder().build();
        }
        Account account = Account.builder()
                .id(accountRequest.getId())
                .balance(accountRequest.getBalance())
                .city(accountRequest.getCity())
                .currency(accountRequest.getCurrency())
                .customerId(accountRequest.getCustomerId())
                .build();

        return converter.converter(accountRepository.save(account));
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest accountRequest) {
        Customer customer = customerService.getCustomerById(accountRequest.getCustomerId());
        if (customer.getId().equals("") || customer.getId() == null) {
            throw new CustomerNotFoundException("can not find");
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(accountRequest.getBalance());
            account.setCity(accountRequest.getCity());
            account.setCurrency(accountRequest.getCurrency());
            account.setCustomerId(accountRequest.getCustomerId());
            accountRepository.save(account);

        });
        return accountOptional.map(converter::converter).orElseThrow(() -> new CustomerNotFoundException("can not find id" + id));
    }

    public AccountDto getAccountById(String id) {
        return accountRepository.findById(id).map(converter::converter).orElse(new AccountDto());
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(converter::converter).collect(Collectors.toList());
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public AccountDto withDrawMoney(String id, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if (account.getBalance() > amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }

        });
        return accountOptional.map(converter::converter).orElse(new AccountDto());

    }


    public AccountDto addMoney(String id, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);

        });
        return accountOptional.map(converter::converter).orElse(new AccountDto());

    }
}