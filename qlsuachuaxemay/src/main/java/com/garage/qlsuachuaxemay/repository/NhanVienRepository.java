package com.garage.qlsuachuaxemay.repository;

import com.garage.qlsuachuaxemay.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    // Tự động tìm nhân viên dựa trên email và mật khẩu nhập vào
    NhanVien findByEmailAndMatKhau(String email, String matKhau);
}