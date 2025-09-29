package com.bank.dto;

public class CustomerRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String customerPin;
    private String aadharNumber;
    private String dob;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCustomerPin() { return customerPin; }
    public void setCustomerPin(String customerPin) { this.customerPin = customerPin; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
}








//package com.bank.dto;
//
//import jakarta.validation.constraints.*;
//
//public class CustomerRequest {
//
//    @NotBlank
//    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only alphabets")
//    private String name;
//
//    @NotBlank
//    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be 10 digits and start with 6,7,8,9")
//    private String phoneNumber;
//
//    @Email(message = "Invalid email format")
//    private String email;
//
//    private String address;
//
//    @NotBlank
//    private String customerPin;
//
//    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar must be 12 digits")
//    private String aadharNumber;
//
//    private String dob;
//
//    // Getters and Setters
//}
