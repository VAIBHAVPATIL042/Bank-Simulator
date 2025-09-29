package com.bank.serviceImpl;

import com.bank.dao.CustomerRepository;
import com.bank.dao.CustomerRepositoryImpl;
import com.bank.dto.CustomerRequest;
import com.bank.model.Customer;
import com.bank.service.CustomerService;
import com.bank.util.ValidationUtil;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo = new CustomerRepositoryImpl();

    @Override
    public boolean onboardCustomer(CustomerRequest request) throws Exception {
        // validation
        if (!ValidationUtil.validName(request.getName())) throw new IllegalArgumentException("Invalid name");
        if (!ValidationUtil.validPhone(request.getPhoneNumber())) throw new IllegalArgumentException("Invalid phone");
        if (!ValidationUtil.validAadhar(request.getAadharNumber())) throw new IllegalArgumentException("Invalid aadhar");

        Customer c = new Customer();
        c.setName(request.getName());
        c.setPhoneNumber(request.getPhoneNumber());
        c.setEmail(request.getEmail());
        c.setAddress(request.getAddress());
        c.setCustomerPin(request.getCustomerPin());
        c.setAadharNumber(request.getAadharNumber());
        c.setDob(request.getDob());
        return repo.addCustomer(c);
    }

    @Override
    public Customer getCustomerById(long id) throws Exception {
        return repo.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return repo.findAll();
    }

    @Override
    public boolean updateCustomer(long id, CustomerRequest request) throws Exception {
        // optional: validate fields present
        if (!ValidationUtil.validName(request.getName())) throw new IllegalArgumentException("Invalid name");
        if (!ValidationUtil.validPhone(request.getPhoneNumber())) throw new IllegalArgumentException("Invalid phone");

        Customer c = new Customer();
        c.setName(request.getName());
        c.setPhoneNumber(request.getPhoneNumber());
        c.setEmail(request.getEmail());
        c.setAddress(request.getAddress());
        return repo.updateCustomer(id, c);
    }

    @Override
    public boolean deleteCustomer(long id) throws Exception {
        return repo.deleteCustomer(id);
    }
}
