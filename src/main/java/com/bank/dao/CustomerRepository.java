package com.bank.dao;

import com.bank.model.Customer;
import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(Customer customer) throws Exception;
    Customer findById(int id) throws Exception;
    List<Customer> findAll() throws Exception;
    boolean updateCustomer(long id, Customer customer) throws Exception;
    boolean deleteCustomer(long id) throws Exception;
}
