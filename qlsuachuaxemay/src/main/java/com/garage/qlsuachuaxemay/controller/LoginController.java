package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.entity.NhanVien;
import com.garage.qlsuachuaxemay.repository.NhanVienRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("/login")
    public String hienThiTrangDangNhap() {
        return "login";
    }

    @PostMapping("/login")
    public String xuLyDangNhap(@RequestParam("email") String email, 
                               @RequestParam("password") String password, 
                               HttpSession session, Model model) {
        
        // Truy vấn CSDL kiểm tra tài khoản
        NhanVien nv = nhanVienRepository.findByEmailAndMatKhau(email, password);
        
        if (nv != null) {
            // Đăng nhập thành công, lưu vào Session
            session.setAttribute("userLogon", nv);
            return "redirect:/thanhtoan";
        } else {
            // Đăng nhập thất bại, báo lỗi ra màn hình
            model.addAttribute("error", "Email hoặc mật khẩu không chính xác!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}