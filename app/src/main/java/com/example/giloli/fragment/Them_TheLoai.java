package com.example.giloli.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giloli.R;
import com.example.giloli.ThemNhanVien;
import com.example.giloli.adapter.TheLoaiAdapter;
import com.example.giloli.dao.NhanVien_DAO;
import com.example.giloli.dao.TheLoai_DAO;
import com.example.giloli.model.NhanVien;
import com.example.giloli.model.TheLoaiDT;

import java.util.ArrayList;

public class Them_TheLoai extends AppCompatActivity {
    TheLoai_DAO theloaiDAO;
    ArrayList<TheLoaiDT> list;
    TheLoaiAdapter theloaiAdapter;
    Button btnadd;
    EditText edtmatheloai, edttentheloai, edtvitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_theloai);
        setTitle("Thêm thể loại");
        btnadd = findViewById(R.id.btnAdd);
        edtmatheloai = findViewById(R.id.edtmatheloai);
        edttentheloai = findViewById(R.id.edttentheloai);
        edtvitri = findViewById(R.id.edtvitri);
    }
    public void addtheloai(View view){
        theloaiDAO = new TheLoai_DAO(Them_TheLoai.this);
        String manhanvien = edtmatheloai.getText().toString();
        Boolean checkMaNV = theloaiDAO.checkMaNV(manhanvien);
        try {
            if (validation() < 1) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else if (checkMaNV == false) {
                Toast.makeText(getApplicationContext(), "Mã thể loại bị trùng", Toast.LENGTH_SHORT).show();
            } else {
                TheLoaiDT theloai = new
                        TheLoaiDT(edtmatheloai.getText().toString(), edttentheloai.getText().toString(), edtvitri.getText().toString());
                if (theloaiDAO.addTheLoai(theloai) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new
                            Intent(Them_TheLoai.this, Them_Fragment.class);
                    Bundle b = new Bundle();
                    b.putString("MaTL", edtmatheloai.getText().toString());
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
    public int validation(){
        if
        (edtmatheloai.getText().toString().isEmpty()|| edttentheloai.getText().toString().isEmpty()|| edtvitri.getText().toString().isEmpty()
        ){
            return -1;
        }
        return 1;
    }
}
