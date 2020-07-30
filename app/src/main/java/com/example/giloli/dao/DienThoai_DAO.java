package com.example.giloli.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.giloli.database.DBHelper;
import com.example.giloli.model.DienThoai;

import java.util.ArrayList;

public class DienThoai_DAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public static final String SQL_DIENTHOAI="CREATE TABLE DIENTHOAI(TenDT text not null," +
            "MaDT text primary key not null, SoLuong int not null, Gia float not null)";
    public DienThoai_DAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //them Dien thoai
    public long addDinThoai(DienThoai dienThoai){
        SQLiteDatabase database;
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenDT",dienThoai.getTenDT());
        values.put("MaDT", dienThoai.getMaDT());
        values.put("SoLuong", dienThoai.getSoLuong());
        values.put("Gia", dienThoai.getGia());
        return db.insert("DienThoai", null, values);
    }

    //lay Tat ca danh sach dienthoai
    public ArrayList<DienThoai> laytatcaDienThoai() {
        ArrayList<DienThoai> danhsachsach = new ArrayList<>();
        Cursor cr = db.query("DienThoai", null, null,null,null,null,null);
        while(cr.moveToNext()){
            DienThoai dienThoai = new DienThoai();
            dienThoai.setTenDT(cr.getString(0));
            dienThoai.setMaDT(cr.getString(1));
            dienThoai.setSoLuong(cr.getString(2));
            dienThoai.setGia(cr.getString(3));
            danhsachsach.add(dienThoai);
        }
        return danhsachsach;
    }
    // update ddien thoai
    public int updateDienThoai(DienThoai dienThoai){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenDT",dienThoai.getTenDT());
        values.put("MaDT",dienThoai.getMaDT());
        values.put("SoLuong",dienThoai.getSoLuong());
        values.put("Gia",dienThoai.getGia());
        return  db.update("DienThoai", values, "MaDT=?", new
                String[]{dienThoai.getMaDT()});
    }
    //check trung maDt
    public Boolean checkDienThoai(String MaDT){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DIENTHOAI where MaDT=?", new String[]{MaDT});
        if(cursor.getCount()>0) return false;
        else return true;
    }

        //delete dienthoai
    public int deleteDienThoai(DienThoai dienThoai){
        return  db.delete("DIENTHOAI", "MaDT=?", new
                String[]{String.valueOf(dienThoai.getMaDT())});
    }

}
