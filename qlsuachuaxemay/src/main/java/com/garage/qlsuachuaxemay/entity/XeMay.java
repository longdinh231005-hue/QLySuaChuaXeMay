package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "XeMay")
public class XeMay {
    @Id
    @Column(name = "BienSo")
    private String bienSo;

    // Khóa ngoại liên kết với Khách Hàng
    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    @Column(name = "HangXe")
    private String hangXe;

    @Column(name = "DongXe")
    private String dongXe;

    @Column(name = "SoKhung")
    private String soKhung;

    @Column(name = "SoMay")
    private String soMay;

    @Column(name = "TrangThai")
    private String trangThai;

    // Getters và Setters
    public String getBienSo() { return bienSo; }
    public void setBienSo(String bienSo) { this.bienSo = bienSo; }
    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }
    public String getHangXe() { return hangXe; }
    public void setHangXe(String hangXe) { this.hangXe = hangXe; }
    public String getDongXe() { return dongXe; }
    public void setDongXe(String dongXe) { this.dongXe = dongXe; }
    public String getSoKhung() { return soKhung; }
    public void setSoKhung(String soKhung) { this.soKhung = soKhung; }
    public String getSoMay() { return soMay; }
    public void setSoMay(String soMay) { this.soMay = soMay; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}