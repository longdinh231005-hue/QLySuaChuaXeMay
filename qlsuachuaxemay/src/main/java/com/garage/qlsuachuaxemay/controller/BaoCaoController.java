package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.service.HoaDonService;
import com.garage.qlsuachuaxemay.service.PhieuSuaChuaService;
import com.garage.qlsuachuaxemay.service.PhuTungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private PhieuSuaChuaService phieuService;
    @Autowired
    private PhuTungService phuTungService;

    @GetMapping("/baocao")
    public String trangTongQuan() { return "baocao-thongke"; }

    @GetMapping("/baocao-doanhthu")
    public String baoCaoDoanhThu(Model model) { 
        model.addAttribute("dsHoaDon", hoaDonService.getAllHoaDon());
        return "baocao-doanhthu"; 
    }

    @GetMapping("/baocao-suachua")
    public String baoCaoSuachua(Model model) { 
        model.addAttribute("dsPhieu", phieuService.getAllPhieuSuaChua());
        return "baocao-suachua"; 
    }

    @GetMapping("/baocao-tonkho")
    public String baoCaoTonKho(Model model) { 
        model.addAttribute("dsPhuTung", phuTungService.getPhuTungCanNhap());
        return "baocao-tonkho"; 
    }
}