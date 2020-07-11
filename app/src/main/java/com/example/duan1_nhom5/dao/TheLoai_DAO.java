package com.example.duan1_nhom5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1_nhom5.database.DBHelper;
import com.example.duan1_nhom5.model.TheLoaiDT;

import java.util.ArrayList;

public class TheLoai_DAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public static final String SQL_THELOAI="CREATE TABLE THELOAI(TenTL text," +
            "MaTL text primary key, ViTri text not null)";
    public TheLoai_DAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //add them the loai

    public long addTheLoai(TheLoaiDT tl){
        SQLiteDatabase database;
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTL",tl.getMaTL());
        values.put("TenTL", tl.getTenTL());
        values.put("ViTri", tl.getViTri());
        return db.insert("TheLoai", null, values);
    }
// lay tat ca danh sach the loai

    public ArrayList<TheLoaiDT> getAllTheLoai(){
        ArrayList<TheLoaiDT> danhsachtheloai = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr = db.query("TheLoai", null, null, null, null, null, null);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            String MaTL = cr.getString(0);
            String TenTL = cr.getString(1);
            String ViTri = cr.getString(2);
            danhsachtheloai.add(new TheLoaiDT(MaTL,TenTL, ViTri));
            cr.moveToNext();
        }return danhsachtheloai;
    }
    //update the loai

    public int updateTheLoai(TheLoaiDT tl){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTL",tl.getMaTL());
        values.put("TenTL",tl.getTenTL());
        values.put("ViTri", tl.getViTri());
        return  db.update("TheLoai", values, "MaTL=?", new
                String[]{tl.getMaTL()});
    }
    // Xóa The Loai
    public int deleteTheLoai(TheLoaiDT theLoaiDT){
        return  db.delete("TheLoai", "MaTL=?", new
                String[]{String.valueOf(theLoaiDT.getMaTL())});
    }
}
