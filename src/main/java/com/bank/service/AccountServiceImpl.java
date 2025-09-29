//
//package com.bank.service;
//
//import com.bank.dao.AccountRepository;
//import com.bank.dao.AccountRepositoryImpl;
//import com.bank.dto.AccountRequest;
//import com.bank.model.Account;
//
//import java.util.List;
//
//
//public class AccountServiceImpl {
//    private final AccountRepository repo = new AccountRepositoryImpl();
//
//    public boolean createAccount(AccountRequest request) {
//        Account acc = new Account();
//        acc.setCustomerId(request.getCustomerId());
//        acc.setAccountNumber(request.getAccountNumber());
//        acc.setAadharNumber(request.getAadharNumber());
//        acc.setIfscCode(request.getIfscCode());
//        acc.setPhoneNumber(request.getPhoneNumber());
//        acc.setAmount(request.getAmount());
//        acc.setBankName(request.getBankName());
//        acc.setNameOnAccount(request.getNameOnAccount());
//        return repo.createAccount(acc);
//    }
//
//    public boolean updateAccount(String accountNumber, AccountRequest request) {
//        Account acc = new Account();
//        acc.setPhoneNumber(request.getPhoneNumber());
//        acc.setAmount(request.getAmount());
//        acc.setNameOnAccount(request.getNameOnAccount());
//        return repo.updateAccount(accountNumber, acc);
//    }
//
//    public Account getByAccountNumber(String accountNumber) {
//        return repo.findByAccountNumber(accountNumber);
//    }
//
//    public boolean deleteAccount(String accountNumber) {
//        return repo.deleteByAccountNumber(accountNumber);
//    }
//
//    public List<Account> getAllAccounts() {
//        return repo.findAll();
//    }
//}
//





//package com.bank.service;
//
//import com.bank.dao.AccountRepository;
//import com.bank.dao.AccountRepositoryImpl;
//import com.bank.dto.AccountRequest;
//import com.bank.model.Account;
//
//import java.util.List;
//
//public class AccountServiceImpl {
//    private final AccountRepository repo = new AccountRepositoryImpl();
//
//    public boolean createAccount(AccountRequest request) {
//        Account acc = new Account();
//        acc.setCustomerId(request.getCustomerId());
//        acc.setAccountNumber(request.getAccountNumber());
//        acc.setAadharNumber(request.getAadharNumber());
//        acc.setIfscCode(request.getIfscCode());
//        acc.setPhoneNumber(request.getPhoneNumber());
//        acc.setAmount(request.getAmount());
//        acc.setBankName(request.getBankName());
//        acc.setNameOnAccount(request.getNameOnAccount());
//        return repo.createAccount(acc);
//    }
//
//    public boolean updateAccount(String accountNumber, AccountRequest request) {
//        Account acc = new Account();
//        acc.setPhoneNumber(request.getPhoneNumber());
//        acc.setAmount(request.getAmount());
//        acc.setNameOnAccount(request.getNameOnAccount());
//        return repo.updateAccount(accountNumber, acc);
//    }
//
//    public Account getByAccountNumber(String accountNumber) {
//        return repo.findByAccountNumber(accountNumber);
//    }
//
//    public boolean deleteAccount(String accountNumber) {
//        return repo.deleteByAccountNumber(accountNumber);
//    }
//
//    public List<Account> getAllAccounts() {
//        return repo.findAll();
//    }
//}
