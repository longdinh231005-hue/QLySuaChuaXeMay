package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.entity.PhieuSuaChua;
import com.garage.qlsuachuaxemay.entity.HoaDon;
import com.garage.qlsuachuaxemay.service.PhieuSuaChuaService;
import com.garage.qlsuachuaxemay.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ThanhToanController {

    @Autowired
    private PhieuSuaChuaService phieuService;

    @Autowired
    private HoaDonService hoaDonService;

    // LẤY HẾT DATA PHIẾU SỬA CHỮA ĐỔ LÊN TRANG THANH TOÁN (Yêu cầu 3)
    @GetMapping("/thanhtoan")
    public String hienThiTrangThanhToan(Model model) {
        List<PhieuSuaChua> dsPhieu = phieuService.getAllPhieuSuaChua();
        model.addAttribute("listPhieu", dsPhieu); // Gửi danh sách sang HTML
        return "thanhtoan";
    }

    // LẤY HẾT DATA HÓA ĐƠN ĐỔ LÊN TRANG LỊCH SỬ (Yêu cầu 4)
    @GetMapping("/lichsu-hoadon")
    public String hienThiLichSu(Model model) {
        List<HoaDon> dsHoaDon = hoaDonService.getAllHoaDon();
        model.addAttribute("listHoaDon", dsHoaDon); // Gửi danh sách sang HTML
        return "lichsu-hoadon";
    }
}