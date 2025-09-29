package com.bank.service;

import com.bank.dao.CustomerRepository;
import com.bank.dao.CustomerRepositoryImpl;
import com.bank.dto.CustomerRequest;
import com.bank.model.Customer;

import java.util.List;

public class CustomerServiceImpl {

    private final CustomerRepository repo = new CustomerRepositoryImpl();

    // Onboard customer
    public boolean onboardCustomer(CustomerRequest request) {
        try {
            Customer c = new Customer();
            c.setName(request.getName());
            c.setPhoneNumber(request.getPhoneNumber());
            c.setEmail(request.getEmail());
            c.setAddress(request.getAddress());
            c.setCustomerPin(request.getCustomerPin());
            c.setAadharNumber(request.getAadharNumber());
            c.setDob(request.getDob());
            return repo.addCustomer(c);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get customer by ID
    public Customer getCustomer(long id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update customer
    public boolean updateCustomer(long id, CustomerRequest request) {
        try {
            Customer c = new Customer();
            c.setName(request.getName());
            c.setPhoneNumber(request.getPhoneNumber());
            c.setEmail(request.getEmail());
            c.setAddress(request.getAddress());
            return repo.updateCustomer(id, c);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}