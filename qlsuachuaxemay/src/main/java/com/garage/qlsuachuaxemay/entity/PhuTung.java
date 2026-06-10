package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PhuTung")
public class PhuTung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPT")
    private int maPT;

    @Column(name = "TenPT")
    private String tenPT;

    @Column(name = "DonViTinh")
    private String donViTinh;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "DonGia")
    private double donGia;

    @Column(name = "TonKhoToiThieu")
    private int tonKhoToiThieu;

    @Column(name = "TrangThai")
    private String trangThai;

    // Getters và Setters
    public int getMaPT() { return maPT; }
    public void setMaPT(int maPT) { this.maPT = maPT; }
    public String getTenPT() { return tenPT; }
    public void setTenPT(String tenPT) { this.tenPT = tenPT; }
    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public int getTonKhoToiThieu() { return tonKhoToiThieu; }
    public void setTonKhoToiThieu(int tonKhoToiThieu) { this.tonKhoToiThieu = tonKhoToiThieu; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}