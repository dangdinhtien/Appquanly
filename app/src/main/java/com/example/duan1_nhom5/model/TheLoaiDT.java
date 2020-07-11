package com.example.duan1_nhom5.model;

public class TheLoaiDT {
    private String MaTL;
    private String TenTL;
    private String ViTri;

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String maTL) {
        MaTL = maTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String tenTL) {
        TenTL = tenTL;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }

    public TheLoaiDT() {
    }

    public TheLoaiDT(String maTL, String tenTL, String viTri) {
        MaTL = maTL;
        TenTL = tenTL;
        ViTri = viTri;
    }
}
