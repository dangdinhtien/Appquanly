package com.example.duan1_nhom5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1_nhom5.database.DBHelper;
import com.example.duan1_nhom5.model.HoaDon;

import java.util.ArrayList;

public class HoaDon_DAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public static final String SQL_HOADON="CREATE TABLE HOADON(MaHD text primary key," +
            "NgayXuatHD date not null,MaDT text , SoluongMua text , foreign key (MaDT) references DienThoai(MaDT))";
    public HoaDon_DAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //add hoa don
    public long addhoadon(HoaDon hd){
        SQLiteDatabase database;
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaHD",hd.getMaHD());
        values.put("NgayXuatHD", hd.getNgayXuatHD());
        values.put("MaDT", hd.getMaDT());
        values.put("SoluongMua",hd.getSoluongMua());
        return db.insert("HoaDon", null, values);
    }
    //lay tat ca hoa don
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr = db.query("HoaDon", null, null, null, null, null, null);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            String MaHD = cr.getString(0);
            String NgayXuatHD = cr.getString(1);
            String MaDT = cr.getString(2);
            String SoluongMua = cr.getString(3);

            danhsachhoadon.add(new HoaDon(MaHD, NgayXuatHD,MaDT,SoluongMua));
            cr.moveToNext();
        }
        return danhsachhoadon;
    }

    // update hoa don
    public int updateHoaDon(HoaDon hd){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaDT",hd.getMaDT());
        values.put("MaHD",hd.getMaHD());
        values.put("NgayXuatHD",hd.getNgayXuatHD());
        values.put("SoluongMua",hd.getSoluongMua());
        return  db.update("HoaDon", values, "MaHD=?", new
                String[]{hd.getMaHD()});
    }
//check trung ma hoa don
    public Boolean checkHoadon(String MaHD){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from HoaDon where MaHD=?", new String[]{MaHD});
        if(cursor.getCount()>0) return false;
        else return true;
    }
//delete hoadon
    public int deleteHoadon(HoaDon hoaDon){
        return  db.delete("HoaDon", "MaHD=?", new
                String[]{String.valueOf(hoaDon.getMaHD())});
    }
}
