package com.example.duan1_nhom5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.adapter.NhapDienThoaiAdapter;
import com.example.duan1_nhom5.dao.DienThoai_DAO;
import com.example.duan1_nhom5.model.DienThoai;

import java.util.ArrayList;

public class Fragment_SanPham extends Fragment {
    GridView gv;
    ArrayList<DienThoai> list_dienthoai;
    DienThoai_DAO dienThoai_dao;
    NhapDienThoaiAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_sanpham,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
gv = view.findViewById(R.id.gvsanpham);
dienThoai_dao = new DienThoai_DAO(getContext());
list_dienthoai = new ArrayList<>();
list_dienthoai = dienThoai_dao.laytatcaDienThoai();
adapter = new NhapDienThoaiAdapter(list_dienthoai,getContext());
gv.setAdapter(adapter);


    }
}
