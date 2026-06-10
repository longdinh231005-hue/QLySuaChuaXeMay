package com.garage.qlsuachuaxemay.service.impl;

import com.garage.qlsuachuaxemay.entity.Customer;
import com.garage.qlsuachuaxemay.repository.CustomerRepository;
import com.garage.qlsuachuaxemay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllActiveCustomers() {
        return customerRepository.findByStatusTrue();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllActiveCustomers();
        }
        return customerRepository.searchActiveCustomers(keyword.trim());
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .filter(Customer::isStatus)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại hoặc đã bị xóa."));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new IllegalArgumentException("Số điện thoại này đã tồn tại trong hệ thống.");
        }
        customer.setStatus(true);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customerDetails) {
        Customer existing = getCustomerById(id);
        
        // If phone number changed, validate uniqueness
        if (!existing.getPhoneNumber().equals(customerDetails.getPhoneNumber())) {
            if (customerRepository.existsByPhoneNumber(customerDetails.getPhoneNumber())) {
                throw new IllegalArgumentException("Số điện thoại này đã tồn tại trong hệ thống.");
            }
        }
        
        existing.setFullName(customerDetails.getFullName());
        existing.setPhoneNumber(customerDetails.getPhoneNumber());
        existing.setAddress(customerDetails.getAddress());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer existing = getCustomerById(id);
        existing.setStatus(false); // Soft delete
        customerRepository.save(existing);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
}
