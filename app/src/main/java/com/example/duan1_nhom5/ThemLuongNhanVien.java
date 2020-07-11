package com.example.duan1_nhom5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1_nhom5.dao.Luong_DAO;
import com.example.duan1_nhom5.dao.NhanVien_DAO;
import com.example.duan1_nhom5.model.Luong;
import com.example.duan1_nhom5.model.NhanVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ThemLuongNhanVien extends AppCompatActivity {
    Luong_DAO luong_dao;
    NhanVien_DAO nhanVien_dao;
    Spinner spnMaNV;
    EditText Stt, edtSongaylam;
    String maNhanVien = "";
    List<NhanVien> listNhanVien = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_luongnhanvien);
        setTitle("THÊM LƯƠNG");
        spnMaNV = (Spinner) findViewById(R.id.spnManv);
        try {
            getTheLoai();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Stt = (EditText) findViewById(R.id.edtStt);
        edtSongaylam = (EditText) findViewById(R.id.edtSongaylam);

        //
        spnMaNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int
                    position, long id) {
                maNhanVien =
                        listNhanVien.get(spnMaNV.getSelectedItemPosition()).getMaNV();
                luong_dao = new Luong_DAO(ThemLuongNhanVien.this);
                luong_dao.getAlllLuong̣̣̣̣̣();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            Stt.setText(b.getString("Stt"));
            String maTheLoai = b.getString("MaNV");
            edtSongaylam.setText(b.getString("SoNgayLam"));

            spnMaNV.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

    public void getTheLoai() throws ParseException {
        nhanVien_dao = new NhanVien_DAO(ThemLuongNhanVien.this);
        listNhanVien = NhanVien_DAO.getAllnhanvien();
        ArrayAdapter<NhanVien> dataAdapter = new ArrayAdapter<NhanVien>(this,
                android.R.layout.simple_spinner_item, listNhanVien);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaNV.setAdapter(dataAdapter);
    }
    public void addLuong(View view){
        String maluong= Stt.getText().toString();
        Boolean checkmaluong = luong_dao.checkluong(maluong);
        luong_dao = new Luong_DAO(ThemLuongNhanVien.this);
        Luong sach = new
                Luong(Stt.getText().toString(),maNhanVien,edtSongaylam.getText().toString());
        try {
            if  (checkmaluong == false) {
                Toast.makeText(this, "Mã lương đã tồn tại", Toast.LENGTH_LONG).show();
                return;
            }else if (luong_dao.insertLuong(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void thoat(View view){
        finish();
    }
    public void showLuong(View view){
        finish();
    }
    public int checkPositionTheLoai(String strTheLoai){
        for (int i = 0; i <listNhanVien.size(); i++){
            if (strTheLoai.equals(listNhanVien.get(i).getMaNV())){
                return i;
            }
        }
        return 0;
    }
}
