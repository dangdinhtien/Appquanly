package com.example.duan1_nhom5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom5.database.DBHelper;
import com.example.duan1_nhom5.model.HoaDonChiTiet;

public class HoaDonChiTiet_DAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public static final String SQL_HOADONCHITIET="CREATE TABLE HOADONCHITIET(MaHDCT integer primary key autoincrement," +
            "SoLuongBan int not null," +
            " MaDT text not null," +
            "TenDT text not null," +
            "MaHD text not null,"+
            "foreign key (MaHD) references HOADON(MaHD),"+
            "foreign key (MaDT) references DIENTHOAI(MaDT))";
    public HoaDonChiTiet_DAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//add hoa don chi tiet
public long addhoadonct(HoaDonChiTiet hdct){
    SQLiteDatabase database;
    db=dbHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("MaHDCT",hdct.getMaHDCT());
    values.put("MaHD", hdct.getMaHD());
    values.put("SoLuongBan", hdct.getSoLuongBan());
    values.put("MaDT", hdct.getMaDT());
    values.put("TenDT", hdct.getTenDT());
    return db.insert("Fragment_HoaDon", null, values);
}

}
