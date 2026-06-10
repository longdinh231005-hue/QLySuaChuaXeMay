package com.garage.qlsuachuaxemay.service;

import com.garage.qlsuachuaxemay.entity.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllActiveCustomers();
    List<Customer> searchCustomers(String keyword);
    Customer getCustomerById(int id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(int id, Customer customerDetails);
    void deleteCustomer(int id);
    boolean existsByPhoneNumber(String phoneNumber);
}
