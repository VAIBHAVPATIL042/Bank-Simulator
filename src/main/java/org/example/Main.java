package org.example;

import com.bank.dto.CustomerRequest;
import com.bank.service.CustomerServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomerServiceImpl service = new CustomerServiceImpl();

        CustomerRequest request = new CustomerRequest();
        request.setName("Vaibhav Patil");
        request.setPhoneNumber("8468910966");
        request.setEmail("vaibhav@gmail.com");
        request.setAddress("Dhule, India");
        request.setCustomerPin("1234");
        request.setAadharNumber("123456789012");
        request.setDob("2004-03-27");

        // Onboard a new customer
        service.onboardCustomer(request);

        // Get all customers
        service.getAllCustomers().forEach(c ->
                System.out.println(c.getCustomerId() + " " + c.getName())
        );
    }
}
