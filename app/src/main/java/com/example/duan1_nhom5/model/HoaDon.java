package com.example.duan1_nhom5.model;

public class HoaDon {
    private String MaHD;
    private String NgayXuatHD;
    private String MaDT;
    private String SoluongMua;

    public String getSoluongMua() {
        return SoluongMua;
    }

    public void setSoluongMua(String soluongMua) {
        SoluongMua = soluongMua;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getNgayXuatHD() {
        return NgayXuatHD;
    }

    public void setNgayXuatHD(String ngayXuatHD) {
        NgayXuatHD = ngayXuatHD;
    }
    public String getMaDT() {
        return MaDT;
    }

    public void setMaDT(String maDT) {
        MaDT = maDT;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD, String ngayXuatHD, String maDT ,String soluongMua) {
        MaHD = maHD;
        MaDT = maDT;
        NgayXuatHD = ngayXuatHD;
        SoluongMua = soluongMua;
    }
}
