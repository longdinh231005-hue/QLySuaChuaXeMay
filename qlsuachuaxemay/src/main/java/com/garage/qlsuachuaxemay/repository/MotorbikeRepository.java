package com.garage.qlsuachuaxemay.repository;

import com.garage.qlsuachuaxemay.entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MotorbikeRepository extends JpaRepository<Motorbike, String> {
    
    // Find active motorbike by exact license plate
    Optional<Motorbike> findByLicensePlateAndStatusTrue(String licensePlate);

    // Check if license plate exists
    boolean existsByLicensePlate(String licensePlate);

    // Search active motorbikes by license plate or owner's name/phone
    @Query("SELECT m FROM Motorbike m WHERE m.status = true AND " +
           "(LOWER(m.licensePlate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(m.customer.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "m.customer.phoneNumber LIKE CONCAT('%', :keyword, '%'))")
    List<Motorbike> searchActiveMotorbikes(@Param("keyword") String keyword);

    // List all active motorbikes
    List<Motorbike> findByStatusTrue();
}
