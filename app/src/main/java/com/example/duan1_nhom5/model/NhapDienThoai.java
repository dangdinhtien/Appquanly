package com.example.duan1_nhom5.model;

public class NhapDienThoai {

    int hinh;
    String ten;
    public NhapDienThoai() {
    }
    public NhapDienThoai(int hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }
    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}

