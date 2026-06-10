package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietSuaChua")
@IdClass(ChiTietSuaChuaId.class) // Gọi lớp khóa kép vừa tạo ở trên
public class ChiTietSuaChua {

    @Id
    @ManyToOne
    @JoinColumn(name = "MaPhieu")
    private PhieuSuaChua phieuSuaChua;

    @Id
    @ManyToOne
    @JoinColumn(name = "MaPT")
    private PhuTung phuTung;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGiaThucTe")
    private double donGiaThucTe;

    @Column(name = "TienCong")
    private double tienCong;

    // Getters và Setters
    public PhieuSuaChua getPhieuSuaChua() { return phieuSuaChua; }
    public void setPhieuSuaChua(PhieuSuaChua phieuSuaChua) { this.phieuSuaChua = phieuSuaChua; }
    public PhuTung getPhuTung() { return phuTung; }
    public void setPhuTung(PhuTung phuTung) { this.phuTung = phuTung; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGiaThucTe() { return donGiaThucTe; }
    public void setDonGiaThucTe(double donGiaThucTe) { this.donGiaThucTe = donGiaThucTe; }
    public double getTienCong() { return tienCong; }
    public void setTienCong(double tienCong) { this.tienCong = tienCong; }
}