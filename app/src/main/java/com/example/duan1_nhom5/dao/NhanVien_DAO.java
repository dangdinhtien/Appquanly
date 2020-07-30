package com.example.duan1_nhom5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.duan1_nhom5.database.DBHelper;
import com.example.duan1_nhom5.model.NhanVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NhanVien_DAO {
    private static SQLiteDatabase db;
    private static DBHelper dbHelper;

    public static final String TABLE_NAME = "NhanVien";
    public static final String SQL_NHANVIEN = "CREATE TABLE NHANVIEN(MaNV text primary key,"+
            "TenNV text,  NgaySinh date, NgayVaoLam date, Sdt text, LuongCoBan float)";
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");

    public NhanVien_DAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    // Thêm NhanVien
    public static int addNhanVien(NhanVien nhanVien){
        ContentValues values = new ContentValues();
        values.put("TenNV",nhanVien.getTenNV());
        values.put("MaNV", nhanVien.getMaNV());
        values.put("NgaySinh", nhanVien.getNgaySinh());
        values.put("NgayVaoLam", sdf.format(nhanVien.getNgayVaoLam()));
        values.put("Sdt", nhanVien.getSDT());
        values.put("LuongCoBan", nhanVien.getLuongCoBan());

        if (checkPrimaryKey(nhanVien.getMaNV())){
            int result = db.update(TABLE_NAME,values,"maSach=?", new String[]{nhanVien.getMaNV()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME,null,values)==-1){
                    return -1;
                }
            }catch (Exception ex){
                Log.e(TAG,ex.toString());
            }
        }
        return 1;
    }
    // Lấy tất cả Nhan Vien
    public static ArrayList<NhanVien> getAllnhanvien() throws ParseException {
        ArrayList<NhanVien> danhsachnhanvien = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr = db.query("NhanVien", null, null,
                null, null, null, null);

        cr.moveToFirst();
        while (!cr.isAfterLast()){
            NhanVien nv = new NhanVien();
            nv.setMaNV(cr.getString(0));
            nv.setTenNV(cr.getString(1));
            nv.setNgaySinh(cr.getString(2));
            nv.setNgayVaoLam(sdf.parse(cr.getString(3)));
            nv.setSDT(cr.getString(4));
            nv.setLuongCoBan(cr.getFloat(5));


            danhsachnhanvien.add(nv);
            Log.d("//======",nv.toString());
            cr.moveToNext();
        }cr.close();
        return danhsachnhanvien;
    }
    public static ArrayList<NhanVien> getAllnhanvienv() throws ParseException {
        ArrayList<NhanVien> danhsachnhanvien = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr = db.query("NhanVien", null, null,
                null, null, null, null);

        cr.moveToFirst();
        while (!cr.isAfterLast()){
            NhanVien nv = new NhanVien();
            nv.setMaNV(cr.getString(0));
            nv.setTenNV(cr.getString(1));
            nv.setNgaySinh(cr.getString(2));
            nv.setNgayVaoLam(sdf.parse(cr.getString(3)));
            nv.setSDT(cr.getString(4));
            nv.setLuongCoBan(cr.getFloat(5));


            danhsachnhanvien.add(nv);
            Log.d("//======",nv.toString());
            cr.moveToNext();
        }cr.close();
        return danhsachnhanvien;
    }

    // check trùng MaNV khi thêm vào database
    public Boolean checkMaNV(String MaNV){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from NhanVien where MaNV=?", new String[]{MaNV});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //check trung sdt
//    public Boolean checkSDT(String SDT){
//        SQLiteDatabase db;
//        db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from NhanVien where SDT=?", new String[]{SDT});
//        if(cursor.getCount()>0) return false;
//        else return true;
//
    // Cập nhật NhanVien
    public int updateNhanVien(NhanVien nv){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaNV", nv.getMaNV());
        values.put("TenNV",nv.getTenNV());
        values.put("NgaySinh",sdf.format(nv.getNgaySinh()));
        values.put("NgayVaoLam",sdf.format(nv.getNgayVaoLam()));
        values.put("Sdt", nv.getSDT());
        values.put("LuongCoBan", nv.getLuongCoBan());

        int result = db.update("NhanVien", values, "MaNV=?", new
                String[]{nv.getMaNV()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    // Xóa Nhanvien
    public int deleteNhanvienByID(String Manv) {
        int result = db.delete("NhanVien", "MaNV=?", new String[]{Manv});
        if (result == 0)
            return -1;
        return 1;
    }

    public int updateInfoNhanVien(String Manv, String TenNV, String NgaySinh, String SDT, float Luongcoban) {
        ContentValues values = new ContentValues();
        values.put("MaNV",Manv);
        values.put("TenNV",TenNV);
        values.put("NgaySinh", NgaySinh);
        values.put("Sdt", SDT);
        values.put("LuongCoBan", Luongcoban);

        int result = db.update("NhanVien",values,"MaNV=?", new
                String[]{Manv});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //check
    public static boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] colums = {"MaNV"};
        //WHERE clause
        String selection = "MaNV=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, colums, selection, selectionArgs, null, null,null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    //getAll
    public static List<NhanVien> getNhanvienchuyencan(String month){
        List<NhanVien> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT MaNV, SUM(soluong) as soluong FROM HOADONCHITIET INNER JOIN HOADON ON HOADON.MaHD = HOADONCHITIET.MaHD WHERE"+
                "strftime ('%m, HOADON.Ngayvaolam) ='" + month+"' "+"GROUP BY MaNV ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Log.d("//=====",c.getString(0));
            NhanVien s = new NhanVien();
            s.setMaNV(c.getString(0));
            s.setTenNV(c.getString(1));
            s.setSDT(c.getString(4));
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
}
