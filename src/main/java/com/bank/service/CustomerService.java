package com.bank.service;

import com.bank.dto.CustomerRequest;
import com.bank.model.Customer;

import java.util.List;

public interface CustomerService {
    boolean onboardCustomer(CustomerRequest request) throws Exception;
    Customer getCustomerById(long id) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    boolean updateCustomer(long id, CustomerRequest request) throws Exception;
    boolean deleteCustomer(long id) throws Exception;
}
