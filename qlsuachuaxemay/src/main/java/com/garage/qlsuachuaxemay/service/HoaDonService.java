package com.garage.qlsuachuaxemay.service;

import com.garage.qlsuachuaxemay.entity.HoaDon;
import com.garage.qlsuachuaxemay.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    // Lấy toàn bộ danh sách hóa đơn cho trang Lịch sử và Báo cáo doanh thu
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    // Lưu hóa đơn mới khi thu ngân bấm xác nhận thanh toán
    public void saveHoaDon(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }
}