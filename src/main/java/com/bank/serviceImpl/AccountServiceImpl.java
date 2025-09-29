package com.bank.serviceImpl;

import com.bank.dao.AccountRepository;
import com.bank.dao.AccountRepositoryImpl;
import com.bank.dto.AccountRequest;
import com.bank.model.Account;
import com.bank.service.AccountService;

import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo = new AccountRepositoryImpl();

    @Override
    public boolean createAccount(AccountRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("AccountRequest cannot be null");
        }

        Account a = new Account();

        // customerId is int everywhere (fixed)
        try {
            a.setCustomerId(Integer.parseInt(request.getCustomerId()));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("customerId must be numeric", ex);
        }

        a.setAccountNumber(Objects.toString(request.getAccountNumber(), null));
        a.setAadharNumber(Objects.toString(request.getAadharNumber(), null));
        a.setIfscCode(Objects.toString(request.getIfscCode(), null));
        a.setPhoneNumber(Objects.toString(request.getPhoneNumber(), null));
        a.setBankName(Objects.toString(request.getBankName(), null));
        a.setNameOnAccount(Objects.toString(request.getNameOnAccount(), null));
        a.setAmount(request.getAmount());

        return repo.createAccount(a);
    }

    @Override
    public List<Account> getByCustomerId(int customerId) {
        return repo.findByCustomerId(customerId);
    }



    @Override
    public boolean updateAccount(String accountNumber, AccountRequest request) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("accountNumber is required");
        }
        if (request == null) {
            throw new IllegalArgumentException("AccountRequest cannot be null");
        }

        Account a = new Account();
        a.setPhoneNumber(Objects.toString(request.getPhoneNumber(), null));
        a.setAmount(request.getAmount());
        a.setNameOnAccount(Objects.toString(request.getNameOnAccount(), null));

        return repo.updateAccount(accountNumber, a);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) return null;
        return repo.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean deleteAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) return false;
        return repo.deleteByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }
}

