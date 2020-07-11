package com.example.duan1_nhom5.model;

public class DienThoai {

    private String MaDT;
    private String TenDT;
    private String SoLuong;
    private String Gia;
    private String MaTL;

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String maTL) {
        MaTL = maTL;
    }

    public String getMaDT() {
        return MaDT;
    }

    public void setMaDT(String maDT) {
        MaDT = maDT;
    }

    public String getTenDT() {
        return TenDT;
    }

    public void setTenDT(String tenDT) {
        TenDT = tenDT;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public DienThoai(String maDT, String tenDT, String soLuong, String gia, String maTL) {
        MaDT = maDT;
        TenDT = tenDT;
        SoLuong = soLuong;
        Gia = gia;
        MaTL = maTL;
    }

    public DienThoai() {
    }
}
