package com.example.duan1_nhom5.fragment;

import android.os.Bundle;
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
        lv = (ListView) view.findViewById(R.id.lvNhanvienchuyencan);
        edThang = (EditText) view.findViewById(R.id.edTthang);
        btnnhanviencc = view.findViewById(R.id.btn);
        btnnhanviencc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nhanvienchuyencan();
            }
        });
    }
    private void Nhanvienchuyencan(){
        if ((edThang.getText().toString()) != null){
            Toast.makeText(getActivity(),"Không đúng tên",Toast.LENGTH_SHORT).show();
        }else {
            nhanVien_dao = new NhanVien_DAO(getActivity());
            dsSach = NhanVien_DAO.getNhanvienchuyencan(edThang.getText().toString());
            adapter = new NhanVienAdapter(getActivity(), (ArrayList<NhanVien>) dsSach);
            lv.setAdapter(adapter);
        }
    }
}
