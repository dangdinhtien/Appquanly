package com.example.duan1_nhom5.model;

public class Luong {
    private String stt;
    private String maNV;
    private String soNgaylam;

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getSoNgaylam() {
        return soNgaylam;
    }

    public void setSoNgaylam(String soNgaylam) {
        this.soNgaylam = soNgaylam;
    }

    public Luong(String stt, String maNV, String soNgaylam) {
        this.stt = stt;
        this.maNV = maNV;
        this.soNgaylam = soNgaylam;
    }

    public Luong() {
    }
}
