package com.example.giloli.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class NhanVien {

    private String MaNV;
    private String TenNV;
    private String NgaySinh;
    private Date NgayVaoLam;
    private String SDT;
    private float LuongCoBan;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        this.MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        this.TenNV = tenNV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.NgaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.NgayVaoLam = ngayVaoLam;
    }

    public float getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        LuongCoBan = luongCoBan;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public NhanVien(String maNV, String tenNV, String ngaySinh, Date ngayVaoLam, String SDT, float luongCoBan) {
        this.MaNV = maNV;
        this.TenNV = tenNV;
        this.NgaySinh = ngaySinh;
        this.NgayVaoLam = ngayVaoLam;
        this.SDT = SDT;
        this.LuongCoBan = luongCoBan;
    }

    public NhanVien() {
    }

    @NonNull
    @Override
    public String toString() {
        return getMaNV();
    }
}
