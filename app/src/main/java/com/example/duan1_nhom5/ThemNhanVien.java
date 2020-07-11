package com.example.duan1_nhom5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1_nhom5.dao.NhanVien_DAO;
import com.example.duan1_nhom5.fragment.NVChuyenCan;
import com.example.duan1_nhom5.model.NhanVien;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemNhanVien extends AppCompatActivity {
    NhanVien_DAO nhanVien_dao;
    EditText edtma, edtten, edtngaysinh, edtngaylam, edtsdt, edtluongcoban;
    Button btnngaysinh, btnngaylam;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_nhanvien);
        setTitle("THÊM NHÂN VIÊN");

        //ánh xạ
        edtma = findViewById(R.id.them_MaNV);
        edtten = findViewById(R.id.them_TenNV);
        edtngaysinh = findViewById(R.id.them_NgaySinh);
        edtngaylam = findViewById(R.id.them_NgayVaoLam);
        edtsdt = findViewById(R.id.them_SDT);
        edtluongcoban = findViewById(R.id.them_Luongcoban);
        btnngaysinh = findViewById(R.id.chonngaysinh);
        btnngaylam = findViewById(R.id.chonngaylam);

        btnngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonngaysinh();
            }
        });
        btnngaylam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonngaylam();
            }
        });
    }

    public void huy(View view){
        finish();
    }

    public void them(View view)  {
        nhanVien_dao = new NhanVien_DAO(ThemNhanVien.this);
        String manhanvien = edtma.getText().toString();
        Boolean checkMaNV = nhanVien_dao.checkMaNV(manhanvien);
        try {
            if (validation() < 1) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }else if (checkMaNV == false){
                Toast.makeText(getApplicationContext(), "Mã nhân viên bị trùng", Toast.LENGTH_SHORT).show();
            }else {
                NhanVien nhanvien = new
                        NhanVien(edtma.getText().toString(),edtten.getText().toString(),edtngaysinh.getText().toString(),sdf.parse(edtngaylam.getText().toString()), edtsdt.getText().toString(),Float.parseFloat(edtluongcoban.getText().toString()));
                if (NhanVien_DAO.addNhanVien(nhanvien) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new
                            Intent(ThemNhanVien.this, NVChuyenCan.class);
                    Bundle b = new Bundle();
                    b.putString("MaNV", edtma.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    private void chonngaysinh () {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaysinh.setText(dayOfMonth + "-" + month + "-" + year);
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();

    }
    private void chonngaylam () {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaylam.setText(dayOfMonth + "-" + month + "-" + year);
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();

    }
    public int validation(){
        if
        (edtma.getText().toString().isEmpty()|| edtten.getText().toString().isEmpty()|| edtngaysinh.getText().toString().isEmpty() || edtngaylam.getText().toString().isEmpty() || edtsdt.getText().toString().isEmpty() || edtluongcoban.getText().toString().isEmpty()
        ){
            return -1;
        }
        return 1;
    }
}
