package com.bank.model;

public class Account {
    private int accountId;
    private int customerId;
    private String accountNumber;
    private String aadharNumber;
    private String ifscCode;
    private String phoneNumber;
    private double amount;
    private String bankName;
    private String nameOnAccount;
    private String status;

    // Getters
    public int getAccountId() { return accountId; }
    public int getCustomerId() { return customerId; }
    public String getAccountNumber() { return accountNumber; }
    public String getAadharNumber() { return aadharNumber; }
    public String getIfscCode() { return ifscCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public double getAmount() { return amount; }
    public String getBankName() { return bankName; }
    public String getNameOnAccount() { return nameOnAccount; }
    public String getStatus() { return status; }

    // Setters
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public void setNameOnAccount(String nameOnAccount) { this.nameOnAccount = nameOnAccount; }
    public void setStatus(String status) { this.status = status; }
}
