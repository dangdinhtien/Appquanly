package com.example.duan1_nhom5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_nhom5.dao.DienThoai_DAO;

import com.example.duan1_nhom5.dao.HoaDon_DAO;
import com.example.duan1_nhom5.dao.Luong_DAO;
import com.example.duan1_nhom5.dao.NhanVien_DAO;
import com.example.duan1_nhom5.dao.TheLoai_DAO;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME= "PhoneManager";
    public static final int VERSION= 1;
    public DBHelper(Context context){
        super(context,DBNAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NhanVien_DAO.SQL_NHANVIEN);
        db.execSQL(DienThoai_DAO.SQL_DIENTHOAI);
        db.execSQL(TheLoai_DAO.SQL_THELOAI);
        db.execSQL(HoaDon_DAO.SQL_HOADON);
        db.execSQL(Luong_DAO.SQL_LUONG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NhanVien_DAO.SQL_NHANVIEN);
        db.execSQL(DienThoai_DAO.SQL_DIENTHOAI);
        db.execSQL(HoaDon_DAO.SQL_HOADON);
        db.execSQL(TheLoai_DAO.SQL_THELOAI);
        db.execSQL(Luong_DAO.SQL_LUONG);
        onCreate(db);
    }
}
