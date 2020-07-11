package com.example.duan1_nhom5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;


import com.example.duan1_nhom5.database.DBHelper;
import com.example.duan1_nhom5.model.Luong;

import java.util.ArrayList;
import java.util.List;

public class Luong_DAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
//foreign key (MaDT) references DienThoai(MaDT)
    public static final String TABLE_NAME = "Luong";
    public static final String SQL_LUONG = "CREATE TABLE Luong (Stt text primary key, MaNV text , SoNgayLam text, foreign key (MaNV) references NhanVien (MaNV) )";
    public static final String TAG = "LuongDAO";
    public Luong_DAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int insertLuong(Luong s){
        ContentValues values = new ContentValues();
        values.put("Stt",s.getStt());
        values.put("MaNV",s.getMaNV());
        values.put("SoNgayLam",s.getSoNgaylam());
        if (checkPrimaryKey(s.getStt())){
            int result = db.update(TABLE_NAME,values,"Stt=?", new String[]{s.getStt()});
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
    //getAll
    public List<Luong> getAlllLuong̣̣̣̣̣(){
        List<Luong> dsLuong = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Luong s = new Luong();
            s.setStt(c.getString(0));
            s.setMaNV(c.getString(1));
            s.setSoNgaylam(c.getString(2));

            dsLuong.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsLuong;
    }
    //update
    public int updateSach(Luong s){
        ContentValues values = new ContentValues();
        values.put("Stt",s.getStt());
        values.put("MaNV",s.getMaNV());
        values.put("SoNgayLam",s.getSoNgaylam());

        int result = db.update(TABLE_NAME,values,"Stt=?", new String[]{s.getStt()});
        if(result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteSachByID(String Stt){
        int result = db.delete(TABLE_NAME,"Stt=?", new String[]{Stt});
        if (result == 0 )
            return -1;
        return 1;
    }
    //check trung ma luong
    public Boolean checkluong(String maluong){
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Luong where Stt=?", new String[]{maluong});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //check
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] colums = {"Stt"};
        //WHERE clause
        String selection = "Stt=?";
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


    public void tinhluong(String mnv, String Songaylam, TextView textView){
        String luong = "SELECT LuongCoBan FROM NhanVien INNER JOIN Luong ON Luong.MaNV = NhanVien.MaNV WHERE Luong.MaNV = " +
                mnv;


        Cursor c = db.rawQuery(luong,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            textView.setText(String.valueOf(c.getFloat(0)* Float.parseFloat(Songaylam))+" VND");
            c.moveToNext();
        }
        c.close();

    }
}
