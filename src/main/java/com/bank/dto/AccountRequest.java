//package com.bank.dto;
//
//public class AccountRequest {
//    private int customerId;
//    private String accountNumber;
//    private String aadharNumber;
//    private String ifscCode;
//    private String phoneNumber;
//    private double amount;
//    private String bankName;
//    private String nameOnAccount;
//
//    // getters and setters
//}



package com.bank.dto;

public class AccountRequest {
    private String customerId;
    private String accountNumber;
    private String aadharNumber;
    private String ifscCode;
    private String phoneNumber;
    private double amount;
    private String bankName;
    private String nameOnAccount;

    // --- Getters and Setters ---
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ✅ This is the alias method you tried to add
    public String getPhoneNumberLinked() {
        return phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }
}




//package com.bank.dto;
//
//public class AccountRequest {
//    private int customerId;
//    private String accountNumber;
//    private String aadharNumber;
//    private String ifscCode;
//    private String phoneNumber;
//    private double amount;
//    private double balance;
//    private String bankName;
//    private String nameOnAccount;
//
//    public int getCustomerId() { return customerId; }
//    public String getAccountNumber() { return accountNumber; }
//    public String getAadharNumber() { return aadharNumber; }
//    public String getIfscCode() { return ifscCode; }
//    public String getPhoneNumber() { return phoneNumber; }
//    public double getAmount() { return amount; }
//    public double getBalance() { return balance; }
//    public String getBankName() { return bankName; }
//    public String getNameOnAccount() { return nameOnAccount; }
//}
//



//package com.bank.dto;
//
//public class AccountRequest {
//    private int customerId;
//    private String accountNumber;
//    private String aadharNumber;
//    private String ifscCode;
//    private String phoneNumberLinked;
//    private Double balance; // optional - if null, default to DB default 600
//    private String bankName;
//    private String nameOnAccount;
//
//    // Getters & Setters
//    public int getCustomerId() { return customerId; }
//    public void setCustomerId(int customerId) { this.customerId = customerId; }
//
//    public String getAccountNumber() { return accountNumber; }
//    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
//
//    public String getAadharNumber() { return aadharNumber; }
//    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }
//
//    public String getIfscCode() { return ifscCode; }
//    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }
//
//    public String getPhoneNumberLinked() { return phoneNumberLinked; }
//    public void setPhoneNumberLinked(String phoneNumberLinked) { this.phoneNumberLinked = phoneNumberLinked; }
//
//    public Double getBalance() { return balance; }
//    public void setBalance(Double balance) { this.balance = balance; }
//
//    public String getBankName() { return bankName; }
//    public void setBankName(String bankName) { this.bankName = bankName; }
//
//    public String getNameOnAccount() { return nameOnAccount; }
//    public void setNameOnAccount(String nameOnAccount) { this.nameOnAccount = nameOnAccount; }
//}
