package com.example.giloli.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.giloli.Login_quanly;
import com.example.giloli.R;
import com.example.giloli.ThemLuongNhanVien;
import com.example.giloli.ThemNhanVien;

public class Them_Fragment extends Fragment {
    TextView tvthemtheloai, tvthemsanpham, tvtinhluong, tvthemnhanvien;
    Button btndangxuat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.them_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Thêm");
        tvthemsanpham = view.findViewById(R.id.tv_them_sanpham);
        tvthemtheloai = view.findViewById(R.id.tv_them_theloai);
        tvtinhluong = view.findViewById(R.id.tv_tinhluong_nhanvien);
        tvthemnhanvien = view.findViewById(R.id.tv_them_nhanvien);
        btndangxuat = view.findViewById(R.id.btnDangXuat);
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangxuat();
            }
        });


        tvthemsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LuongNhanVien.class);
                startActivity(intent);
            }
        });
        tvthemnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemNhanVien.class);
                startActivity(intent);
            }
        });
        tvthemtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Them_TheLoai.class);
                startActivity(intent);
            }
        });
        tvtinhluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemLuongNhanVien.class);
                startActivity(intent);
            }
        });
    }
    public void dangxuat(){
        Intent intent = new Intent(getActivity(), Login_quanly.class);
        startActivity(intent);
    }
}
