package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.entity.PhieuSuaChua;
import com.garage.qlsuachuaxemay.entity.HoaDon;
import com.garage.qlsuachuaxemay.entity.NhanVien;
import com.garage.qlsuachuaxemay.service.HoaDonService;
import com.garage.qlsuachuaxemay.service.PhieuSuaChuaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;

@Controller
public class ThanhToanController {

    @Autowired
    private PhieuSuaChuaService phieuService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/thanhtoan")
    public String hienThiTrangThanhToan(Model model) {
        model.addAttribute("listPhieu", phieuService.getAllPhieuSuaChua());
        return "thanhtoan";
    }

    @GetMapping("/lichsu-hoadon")
    public String hienThiLichSu(Model model) {
        model.addAttribute("listHoaDon", hoaDonService.getAllHoaDon());
        return "lichsu-hoadon";
    }

    @GetMapping("/thanhtoan/xacnhan/{id}")
    public String xuLyXacNhanThanhToan(@PathVariable("id") int id, HttpSession session) {
        PhieuSuaChua phieu = phieuService.getPhieuById(id);
        
        if (phieu != null && "DaSuaXong".equals(phieu.getTrangThai())) {
            // 1. Cập nhật trạng thái phiếu sửa chữa
            phieu.setTrangThai("DaThanhToan");
            phieuService.savePhieuSuaChua(phieu);
            
            // 2. Tự động sinh ra hóa đơn tương ứng lưu vào bảng HoaDon
            HoaDon hoaDon = new HoaDon();
            hoaDon.setPhieuSuaChua(phieu);
            hoaDon.setNgayThanhToan(LocalDateTime.now());
            
            // Lấy nhân viên thu ngân đang đăng nhập từ session
            NhanVien nvLogon = (NhanVien) session.getAttribute("userLogon");
            if (nvLogon != null) {
                hoaDon.setThuNgan(nvLogon);
            } else {
                // Đề phòng trường hợp chạy test thẳng không qua trang login
                NhanVien macDinh = new NhanVien();
                macDinh.setMaNV(2); // ID của Lê Quang Duy trong file SQL mẫu của bạn
                hoaDon.setThuNgan(macDinh);
            }
            
            // Set giá trị mặc định/giả lập để lưu không lỗi (Sau này tính toán dựa trên ChiTietSuaChua)
            hoaDon.setTongTienPhuTung(200000);
            hoaDon.setTongTienCong(100000);
            hoaDon.setThanhTien(300000);
            
            hoaDonService.saveHoaDon(hoaDon);
        }
        
        return "redirect:/thanhtoan";
    }
}