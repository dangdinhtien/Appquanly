package com.example.giloli.fragment;

import android.app.AlertDialog;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.giloli.R;
import com.example.giloli.adapter.DienThoaiAdapter;
import com.example.giloli.adapter.SpinnerAdapter;
import com.example.giloli.adapter.TheLoaiAdapter;
import com.example.giloli.dao.DienThoai_DAO;
import com.example.giloli.dao.TheLoai_DAO;
import com.example.giloli.model.DienThoai;
import com.example.giloli.model.TheLoaiDT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class Fragment_DienThoai extends Fragment {

    ArrayList<DienThoai> list_dienthoai;
    ArrayList<TheLoaiDT> list_theloai;
    DienThoai_DAO dienThoai_dao;
    EditText edtmasodienthoai, edttendienthoai, edtsoluongdienthoai, edtgia;
    TheLoai_DAO theloaiDAO;
    DienThoaiAdapter dienThoaiAdapter;
    TheLoaiAdapter theloaiAdapter;
    FloatingActionButton fabdienthoai;
    RecyclerView rv_dienthoai;
    Spinner spinner_themdienthoai;
    SpinnerAdapter spinnerAdapter;
    TheLoaiDT tl = new TheLoaiDT();
    Button btnadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dienthoai, container, false);
        getActivity().setTitle("Điện thoại");
        // thể loại
        list_dienthoai = new ArrayList<>();
        list_theloai = new ArrayList<>();
        theloaiDAO = new TheLoai_DAO(getContext());
        try {
            list_theloai = theloaiDAO.getAllTheLoai();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        theloaiAdapter = new TheLoaiAdapter(list_theloai,getContext());
        dienThoai_dao = new DienThoai_DAO(getContext());
        list_dienthoai =dienThoai_dao.laytatcaDienThoai();
        dienThoaiAdapter = new DienThoaiAdapter(list_dienthoai, getContext());
        //
        btnadd = view.findViewById(R.id.btnadd);
        spinnerAdapter = new SpinnerAdapter(list_theloai, getActivity());

        //recycler view
        rv_dienthoai = view.findViewById(R.id.rv_dienthoai_them);
        rv_dienthoai.setHasFixedSize(true);
        rv_dienthoai.setItemAnimator(new ScaleInBottomAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_dienthoai.setLayoutManager(layoutManager);
        rv_dienthoai.setAdapter(dienThoaiAdapter);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                LayoutInflater inflater = Fragment_DienThoai.this.getLayoutInflater();
//                v = inflater.inflate(R.layout.alert_dienthoai, null);
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                v = layoutInflater.inflate(R.layout.alert_dienthoai, null ,true);
                builder.setView(v);

                //----------------------------------------
                //anhsa
                edttendienthoai = v.findViewById(R.id.edttendienthoai_them);
                edtmasodienthoai = v.findViewById(R.id.edtmasodienthoai_them);
                edtsoluongdienthoai = v.findViewById(R.id.edtsoluongdienthoai_them);
                edtgia = v.findViewById(R.id.edtgiadienthoai_them);
                spinner_themdienthoai = v.findViewById(R.id.spinner_themdienthoai);
                spinnerAdapter = new SpinnerAdapter(list_theloai, getActivity()) {
                };
                spinner_themdienthoai.setAdapter(spinnerAdapter);
                //--------------------------------------
                list_dienthoai = new ArrayList<>();
                dienThoai_dao = new DienThoai_DAO(getActivity());
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String madt = edtmasodienthoai.getText().toString();
                        Boolean checkdt = dienThoai_dao.checkDienThoai(madt);
                        if (spinner_themdienthoai.getSelectedItem().toString().length() == 0 || edtmasodienthoai.length() == 0 || edttendienthoai.length() == 0
                                || edtsoluongdienthoai.length()==0||edtgia.length()==0 ) {
                            Toast.makeText(getContext(), "Chưa nhập thông tin", Toast.LENGTH_LONG).show();
                        } else if (checkdt == false) {
                            Toast.makeText(getContext(), "Mã Điện Thoại đã tồn tại", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            DienThoai dienThoai = new DienThoai();
                            dienThoai.setMaTL(spinner_themdienthoai.getSelectedItem().toString());
                            dienThoai.setMaDT(edtmasodienthoai.getText().toString());
                            dienThoai.setTenDT(edttendienthoai.getText().toString());
                            dienThoai.setSoLuong(edtsoluongdienthoai.getText().toString());
                            dienThoai.setGia(edtgia.getText().toString());
                            if (dienThoai_dao.addDinThoai(dienThoai) == -1) {
                                Toast.makeText(getActivity(), "Thêm điện thoại không thành công", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getActivity(), "Thêm điện thoại " + edttendienthoai.getText().toString() + " thành công", Toast.LENGTH_SHORT).show();
                            list_dienthoai = dienThoai_dao.laytatcaDienThoai();
                            dienThoaiAdapter = new DienThoaiAdapter(list_dienthoai, getContext());
                            rv_dienthoai.setAdapter(dienThoaiAdapter);
                        }
                    }
                });
                builder.show();
            }
        });

        return view;

    }
}
