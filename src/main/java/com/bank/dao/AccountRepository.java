package com.bank.dao;

import com.bank.model.Account;
import java.util.List;

public interface AccountRepository {

    boolean createAccount(Account account);

    List<Account> findByCustomerId(int customerId);

    boolean updateAccount(String accountNumber, Account account);

    Account findByAccountNumber(String accountNumber);

    boolean deleteByAccountNumber(String accountNumber);

    List<Account> findAll();
}
