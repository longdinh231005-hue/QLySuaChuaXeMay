package com.garage.qlsuachuaxemay.service;

import com.garage.qlsuachuaxemay.entity.PhieuSuaChua;
import com.garage.qlsuachuaxemay.repository.PhieuSuaChuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhieuSuaChuaService {

    @Autowired
    private PhieuSuaChuaRepository phieuSuaChuaRepository;

    // Lấy toàn bộ danh sách phiếu sửa chữa hiển thị ra trang Thanh Toán
    public List<PhieuSuaChua> getAllPhieuSuaChua() {
        return phieuSuaChuaRepository.findAll();
    }

    // Tìm 1 phiếu cụ thể theo Mã
    public PhieuSuaChua getPhieuById(int id) {
        Optional<PhieuSuaChua> phieu = phieuSuaChuaRepository.findById(id);
        return phieu.orElse(null);
    }

    // Lưu hoặc cập nhật trạng thái phiếu
    public void savePhieuSuaChua(PhieuSuaChua phieuSuaChua) {
        phieuSuaChuaRepository.save(phieuSuaChua);
    }
}