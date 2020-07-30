package com.example.duan1_nhom5.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.adapter.NhanVienAdapter;
import com.example.duan1_nhom5.dao.NhanVien_DAO;
import com.example.duan1_nhom5.model.NhanVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class NVChuyenCan extends Fragment {
    public static List<NhanVien> dsSach = new ArrayList<>();
    ListView lv;
    NhanVienAdapter adapter = null;
    NhanVien_DAO nhanVien_dao;
    EditText edThang;
    Button btnnhanviencc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nvchuyencan,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) view.findViewById(R.id.lvNhanvien);
        edThang = (EditText) view.findViewById(R.id.edTthang);
        btnnhanviencc = view.findViewById(R.id.btn);
        lv = view.findViewById(R.id.lvNhanvien);

        nhanVien_dao = new NhanVien_DAO(getContext());
        try {
            dsSach = NhanVien_DAO.getAllnhanvienv();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new NhanVienAdapter(getActivity(), (ArrayList<NhanVien>) dsSach);
        lv.setAdapter(adapter);

        lv.setTextFilterEnabled(true);
        edThang.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        dsSach.clear();
        try {
            dsSach = nhanVien_dao.getAllnhanvien();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            adapter.changeDataset(nhanVien_dao.getAllnhanvien());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
