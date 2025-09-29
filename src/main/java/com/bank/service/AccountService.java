package com.bank.service;

import com.bank.dto.AccountRequest;
import com.bank.model.Account;

import java.util.List;

public interface AccountService {
    boolean createAccount(AccountRequest request);

    List<Account> getByCustomerId(int customerId);

    boolean updateAccount(String accountNumber, AccountRequest request);

    Account getByAccountNumber(String accountNumber);

    boolean deleteAccount(String accountNumber);

    List<Account> getAllAccounts();
}
