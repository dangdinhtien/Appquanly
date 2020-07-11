package com.example.duan1_nhom5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1_nhom5.dao.NhanVien_DAO;

import java.util.Calendar;


public class SuaThongTinNV extends AppCompatActivity {
    EditText edtten, edtngaysinh, edtsdt, edtluongcoban;
    Button btnngaysinh;
    TextView txtma;
    NhanVien_DAO nhanVien_dao;
    String ma, ten, ngaysinh, sdt, luongcoban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_thongtin_nhanvien);
        setTitle("Sửa Thông tin");
        //ánh xạ

        txtma = findViewById(R.id.edtma);
        edtten = findViewById(R.id.edtten);
        edtngaysinh = findViewById(R.id.edtngaysinh);
        edtsdt = findViewById(R.id.edtsdt);
        edtluongcoban = findViewById(R.id.edtluongcoban);
        btnngaysinh = findViewById(R.id.btnngaysinh);
        btnngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonngaysinh();
            }
        });

        nhanVien_dao = new NhanVien_DAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ma = bundle.getString("MaNV");
        ten = bundle.getString("TenNV");
        ngaysinh = bundle.getString("NgaySinh");
        sdt = bundle.getString("Sdt");
        luongcoban = bundle.getString("LuongCoBan");
        txtma.setText(ma);
        edtten.setText(ten);
        edtngaysinh.setText(ngaysinh);
        edtsdt.setText(sdt);
        edtluongcoban.setText(luongcoban);
    }

    public void updateUser(View view) {
        if (nhanVien_dao.updateInfoNhanVien(ma, edtten.getText().toString(), edtngaysinh.getText().toString(), edtsdt.getText()
                .toString(), Float.parseFloat(edtluongcoban.getText().toString())) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
    }

    public void Huy(View view) {
        finish();
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
}
