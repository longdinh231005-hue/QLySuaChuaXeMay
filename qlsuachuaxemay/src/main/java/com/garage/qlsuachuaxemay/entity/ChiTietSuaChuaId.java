package com.garage.qlsuachuaxemay.entity;

import java.io.Serializable;
import java.util.Objects;

// Lớp này đóng vai trò là Khóa kép cho bảng ChiTietSuaChua
public class ChiTietSuaChuaId implements Serializable {
    private int phieuSuaChua; 
    private int phuTung;

    public ChiTietSuaChuaId() {}

    public ChiTietSuaChuaId(int phieuSuaChua, int phuTung) {
        this.phieuSuaChua = phieuSuaChua;
        this.phuTung = phuTung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietSuaChuaId that = (ChiTietSuaChuaId) o;
        return phieuSuaChua == that.phieuSuaChua && phuTung == that.phuTung;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phieuSuaChua, phuTung);
    }
}