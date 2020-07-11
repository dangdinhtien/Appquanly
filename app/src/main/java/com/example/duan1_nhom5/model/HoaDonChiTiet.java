package com.example.duan1_nhom5.model;

public class HoaDonChiTiet {
    private String MaHD;
    private String MaHDCT;
    private String MaDT;
    private String TenDT;
    private String SoLuongBan;


    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) { MaHD = maHD; }


    public String getMaDT() {
        return MaDT;
    }

    public void setMaDT(String maDT) { MaDT = maDT; }


    public String getTenDT() {
        return TenDT;
    }

    public void setTenDT(String tenDT) {
        TenDT = tenDT;
    }


    public String getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        MaHDCT = maHDCT;
    }

    public String getSoLuongBan() {
        return SoLuongBan;
    }

    public void setSoLuongBan(String soLuongBan) {
        SoLuongBan = soLuongBan;
    }

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHDCT, String soLuongBan, String maDT, String tenDT, String maHD) {
        MaHDCT = maHDCT;
        MaDT = maDT;
        TenDT = tenDT;
        MaHD = maHD;
        SoLuongBan = soLuongBan;
    }
}
