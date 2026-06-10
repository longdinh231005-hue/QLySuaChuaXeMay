package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "XeMay")
public class Motorbike {
    @Id
    @Column(name = "BienSo")
    private String licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKH", nullable = false)
    private Customer customer;

    @Column(name = "HangXe")
    private String brand;

    @Column(name = "DongXe")
    private String model;

    @Column(name = "SoKhung")
    private String frameNumber;

    @Column(name = "SoMay")
    private String engineNumber;

    @Column(name = "TrangThai")
    @Convert(converter = StatusConverter.class)
    private boolean status = true; // true: HoatDong (Active), false: NgungHoatDong (Inactive)

    // Getters and Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Alias Getters and Setters for backward compatibility with other modules/views
    public String getBienSo() { return licensePlate; }
    public void setBienSo(String bienSo) { this.licensePlate = bienSo; }
    
    public String getHangXe() { return brand; }
    public void setHangXe(String hangXe) { this.brand = hangXe; }
    
    public String getDongXe() { return model; }
    public void setDongXe(String dongXe) { this.model = dongXe; }
    
    public String getSoKhung() { return frameNumber; }
    public void setSoKhung(String soKhung) { this.frameNumber = soKhung; }
    
    public String getSoMay() { return engineNumber; }
    public void setSoMay(String soMay) { this.engineNumber = soMay; }
    
    public String getTrangThai() { return status ? "HoatDong" : "NgungHoatDong"; }
    public void setTrangThai(String trangThai) { this.status = "HoatDong".equalsIgnoreCase(trangThai); }
    
    public Customer getKhachHang() { return customer; }
    public void setKhachHang(Customer customer) { this.customer = customer; }
}
