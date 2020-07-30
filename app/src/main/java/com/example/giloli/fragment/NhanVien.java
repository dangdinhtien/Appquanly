package com.example.giloli.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.giloli.R;
import com.example.giloli.SuaThongTinNV;
import com.example.giloli.adapter.NhanVienAdapter;
import com.example.giloli.dao.NhanVien_DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhanVien extends Fragment {
    NhanVien_DAO nhanVien_dao;
    ArrayList<com.example.giloli.model.NhanVien> list;
    NhanVienAdapter nhanVienAdapter;
    ListView lv;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nhanvien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle("Nhân Viên");

        lv = view.findViewById(R.id.lvNhanVien);
        nhanVien_dao = new NhanVien_DAO(getContext());
        try {
            list = nhanVien_dao.getAllnhanvien();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nhanVienAdapter = new NhanVienAdapter((Activity) getContext(), list);
        lv.setAdapter(nhanVienAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                com.example.giloli.model.NhanVien nhanVien = (com.example.giloli.model.NhanVien) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), SuaThongTinNV.class);
                Bundle b = new Bundle();
                b.putString("MaNV", list.get(position).getMaNV());
                b.putString("TenNV", list.get(position).getTenNV());
                b.putString("NgaySinh", list.get(position).getNgaySinh());
                b.putString("NgayVaoLam", sdf.format(list.get(position).getNgayVaoLam()));
                b.putString("Sdt", list.get(position).getSDT());
                b.putString("LuongCoBan", String.valueOf(nhanVien.getLuongCoBan()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
                    position, long id) {
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        try {
            list = nhanVien_dao.getAllnhanvien();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            nhanVienAdapter.changeDataset(nhanVien_dao.getAllnhanvien());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}