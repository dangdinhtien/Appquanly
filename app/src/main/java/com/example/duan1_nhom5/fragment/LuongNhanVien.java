package com.example.duan1_nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.ThemLuongNhanVien;
import com.example.duan1_nhom5.adapter.LuongAdapter;
import com.example.duan1_nhom5.dao.Luong_DAO;
import com.example.duan1_nhom5.model.Luong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LuongNhanVien extends Fragment {
    public static List<Luong> dsLuong = new ArrayList<>();
    List<NhanVien> nhanvien = new ArrayList<>();
    ListView lvLuong;
    LuongAdapter adapter = null;
    Luong_DAO luong_dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragnment_luongnhanvien, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Nhân Viên");
        lvLuong = (ListView) view.findViewById(R.id.lvLuong);
        luong_dao = new Luong_DAO(getContext());
        dsLuong = luong_dao.getAlllLuong̣̣̣̣̣();
        adapter = new LuongAdapter(getActivity(), dsLuong);
        lvLuong.setAdapter(adapter);
        FloatingActionButton mfab = view.findViewById(R.id.floating);

        mfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemLuongNhanVien.class);
                startActivity(intent);
            }
        });
        lvLuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Luong luong = (Luong) parent.getItemAtPosition(position);
                Intent intent = new
                        Intent(getActivity(),ThemLuongNhanVien.class);
                Bundle b = new Bundle();
                b.putString("Stt", luong.getStt());
                b.putString("MaNV", luong.getMaNV());
                b.putString("SoNgayLam", luong.getSoNgaylam());

                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}