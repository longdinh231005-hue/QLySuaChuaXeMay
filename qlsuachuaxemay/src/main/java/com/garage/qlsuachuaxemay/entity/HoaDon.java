package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHD")
    private int maHD;

    // Quan hệ 1-1: Mỗi hóa đơn thuộc về 1 phiếu sửa chữa duy nhất
    @OneToOne
    @JoinColumn(name = "MaPhieu", unique = true)
    private PhieuSuaChua phieuSuaChua;

    @ManyToOne
    @JoinColumn(name = "MaNV_ThuNgan")
    private NhanVien thuNgan;

    @Column(name = "NgayThanhToan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "TongTienPhuTung")
    private double tongTienPhuTung;

    @Column(name = "TongTienCong")
    private double tongTienCong;

    @Column(name = "ThanhTien")
    private double thanhTien;

    // Getters và Setters
    public int getMaHD() { return maHD; }
    public void setMaHD(int maHD) { this.maHD = maHD; }
    public PhieuSuaChua getPhieuSuaChua() { return phieuSuaChua; }
    public void setPhieuSuaChua(PhieuSuaChua phieuSuaChua) { this.phieuSuaChua = phieuSuaChua; }
    public NhanVien getThuNgan() { return thuNgan; }
    public void setThuNgan(NhanVien thuNgan) { this.thuNgan = thuNgan; }
    public LocalDateTime getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(LocalDateTime ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }
    public double getTongTienPhuTung() { return tongTienPhuTung; }
    public void setTongTienPhuTung(double tongTienPhuTung) { this.tongTienPhuTung = tongTienPhuTung; }
    public double getTongTienCong() { return tongTienCong; }
    public void setTongTienCong(double tongTienCong) { this.tongTienCong = tongTienCong; }
    public double getThanhTien() { return thanhTien; }
    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }
}