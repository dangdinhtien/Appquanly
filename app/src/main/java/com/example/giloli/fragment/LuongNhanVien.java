package com.example.giloli.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.giloli.R;
import com.example.giloli.ThemLuongNhanVien;
import com.example.giloli.adapter.LuongAdapter;
import com.example.giloli.dao.Luong_DAO;
import com.example.giloli.model.Luong;
import com.example.giloli.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LuongNhanVien extends AppCompatActivity {
    public static List<Luong> dsLuong = new ArrayList<>();
    List<NhanVien> nhanvien = new ArrayList<>();
    ListView lvLuong;
    LuongAdapter adapter = null;
    Luong_DAO luong_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Danh sách lương nhân viên");
        setContentView(R.layout.fragnment_luongnhanvien);
        lvLuong = (ListView) findViewById(R.id.lvLuong);
        luong_dao = new Luong_DAO(this);
        dsLuong = luong_dao.getAlllLuong̣̣̣̣̣();
        adapter = new LuongAdapter(this, dsLuong);
        lvLuong.setAdapter(adapter);

    }
}