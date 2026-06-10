package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PhieuSuaChua")
public class PhieuSuaChua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieu")
    private int maPhieu;

    @ManyToOne
    @JoinColumn(name = "BienSo")
    private Motorbike xeMay;

    @ManyToOne
    @JoinColumn(name = "MaNV_TiepNhan")
    private NhanVien nvTiepNhan;

    @ManyToOne
    @JoinColumn(name = "MaNV_KyThuat")
    private NhanVien nvKyThuat;

    @Column(name = "NgayTiepNhan")
    private LocalDateTime ngayTiepNhan;

    @Column(name = "MoTaLoi")
    private String moTaLoi;

    @Column(name = "TrangThai")
    private String trangThai;

    // Getters và Setters
    public int getMaPhieu() { return maPhieu; }
    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }
    public Motorbike getXeMay() { return xeMay; }
    public void setXeMay(Motorbike xeMay) { this.xeMay = xeMay; }
    public NhanVien getNvTiepNhan() { return nvTiepNhan; }
    public void setNvTiepNhan(NhanVien nvTiepNhan) { this.nvTiepNhan = nvTiepNhan; }
    public NhanVien getNvKyThuat() { return nvKyThuat; }
    public void setNvKyThuat(NhanVien nvKyThuat) { this.nvKyThuat = nvKyThuat; }
    public LocalDateTime getNgayTiepNhan() { return ngayTiepNhan; }
    public void setNgayTiepNhan(LocalDateTime ngayTiepNhan) { this.ngayTiepNhan = ngayTiepNhan; }
    public String getMoTaLoi() { return moTaLoi; }
    public void setMoTaLoi(String moTaLoi) { this.moTaLoi = moTaLoi; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}