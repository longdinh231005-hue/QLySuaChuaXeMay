package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "XuatKho")
public class XuatKho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuXuat")
    private int maPhieuXuat;

    // Liên kết với Phiếu sửa chữa (1 phiếu sửa chữa có thể có 1 hoặc nhiều lần xuất kho)
    @ManyToOne
    @JoinColumn(name = "MaPhieuSuaChua", nullable = false)
    private PhieuSuaChua phieuSuaChua;

    // Liên kết với Nhân viên (Thủ kho thực hiện xuất)
    @ManyToOne
    @JoinColumn(name = "MaNV_ThuKho", nullable = false)
    private NhanVien thuKho;

    @Column(name = "NgayXuat", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ngayXuat;

    // ----- GETTERS VÀ SETTERS -----

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public PhieuSuaChua getPhieuSuaChua() {
        return phieuSuaChua;
    }

    public void setPhieuSuaChua(PhieuSuaChua phieuSuaChua) {
        this.phieuSuaChua = phieuSuaChua;
    }

    public NhanVien getThuKho() {
        return thuKho;
    }

    public void setThuKho(NhanVien thuKho) {
        this.thuKho = thuKho;
    }

    public LocalDateTime getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDateTime ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
}