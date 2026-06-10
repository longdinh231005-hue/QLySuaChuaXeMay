package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "KhachHang")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKH")
    private int id;

    @Column(name = "HoTen", nullable = false)
    private String fullName;

    @Column(name = "SoDienThoai", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "DiaChi")
    private String address;

    @Column(name = "TrangThai")
    @Convert(converter = StatusConverter.class)
    private boolean status = true; // true: HoatDong (Active), false: NgungHoatDong (Inactive)

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Motorbike> motorbikes;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Motorbike> getMotorbikes() {
        return motorbikes;
    }

    public void setMotorbikes(List<Motorbike> motorbikes) {
        this.motorbikes = motorbikes;
    }

    // Alias Getters and Setters for backward compatibility with other modules/views
    public int getMaKH() { return id; }
    public void setMaKH(int maKH) { this.id = maKH; }
    
    public String getHoTen() { return fullName; }
    public void setHoTen(String hoTen) { this.fullName = hoTen; }
    
    public String getSoDienThoai() { return phoneNumber; }
    public void setSoDienThoai(String soDienThoai) { this.phoneNumber = soDienThoai; }
    
    public String getDiaChi() { return address; }
    public void setDiaChi(String diaChi) { this.address = diaChi; }
    
    public String getTrangThai() { return status ? "HoatDong" : "NgungHoatDong"; }
    public void setTrangThai(String trangThai) { this.status = "HoatDong".equalsIgnoreCase(trangThai); }
}
