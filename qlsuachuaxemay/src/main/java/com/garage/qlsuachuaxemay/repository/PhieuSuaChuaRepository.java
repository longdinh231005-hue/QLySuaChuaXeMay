package com.garage.qlsuachuaxemay.repository;

import com.garage.qlsuachuaxemay.entity.PhieuSuaChua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuSuaChuaRepository extends JpaRepository<PhieuSuaChua, Integer> {
    // Spring Boot tự động hỗ trợ các hàm findAll(), findById(), save()
}