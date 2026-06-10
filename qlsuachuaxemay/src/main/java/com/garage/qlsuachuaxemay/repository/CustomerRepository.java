package com.garage.qlsuachuaxemay.repository;

import com.garage.qlsuachuaxemay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    // Find active customer by exact phone number
    Optional<Customer> findByPhoneNumberAndStatusTrue(String phoneNumber);
    
    // Check if phone number exists
    boolean existsByPhoneNumber(String phoneNumber);
    
    // Search active customers by name or phone number
    @Query("SELECT c FROM Customer c WHERE c.status = true AND " +
           "(LOWER(c.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR c.phoneNumber LIKE CONCAT('%', :keyword, '%'))")
    List<Customer> searchActiveCustomers(@Param("keyword") String keyword);

    // List all active customers
    List<Customer> findByStatusTrue();
}
