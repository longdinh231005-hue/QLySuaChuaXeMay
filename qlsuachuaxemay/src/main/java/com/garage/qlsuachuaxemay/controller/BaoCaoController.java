package com.garage.qlsuachuaxemay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoController {

    @GetMapping("/baocao")
    public String trangTongQuan() { return "baocao-thongke"; }

    @GetMapping("/baocao-doanhthu")
    public String baoCaoDoanhThu() { return "baocao-doanhthu"; }

    @GetMapping("/baocao-suachua")
    public String baoCaoSuachua() { return "baocao-suachua"; }

    @GetMapping("/baocao-tonkho")
    public String baoCaoTonKho() { return "baocao-tonkho"; }
}