package com.garage.qlsuachuaxemay.service;

import com.garage.qlsuachuaxemay.entity.PhuTung;
import com.garage.qlsuachuaxemay.repository.PhuTungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhuTungService {

    @Autowired
    private PhuTungRepository phuTungRepository;

    // Lấy toàn bộ phụ tùng
    public List<PhuTung> getAllPhuTung() {
        return phuTungRepository.findAll();
    }

    // Lọc ra các phụ tùng cần nhập hàng (áp dụng cho trang Báo cáo tồn kho)
    public List<PhuTung> getPhuTungCanNhap() {
        return phuTungRepository.findPhuTungCanNhapHang();
    }
}