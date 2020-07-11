package com.example.duan1_nhom5.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.adapter.HoaDonAdapter;
import com.example.duan1_nhom5.dao.HoaDon_DAO;
import com.example.duan1_nhom5.model.HoaDon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class Fragment_HoaDon extends Fragment {
    HoaDon_DAO hoadon_DAO;
    ArrayList<HoaDon> list;
    HoaDonAdapter hoadonAdapter;
    FloatingActionButton fabhoadon;
    EditText edtngaythem, edtmahoadon,edtmadt,edtsoluongmua;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm--dd");

    public Fragment_HoaDon() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoadon, container, false);

        list = new ArrayList<>();
        hoadon_DAO = new HoaDon_DAO(getActivity());
        list = hoadon_DAO.getAllHoaDon();
        hoadonAdapter = new HoaDonAdapter(list, getContext());
        fabhoadon = view.findViewById(R.id.fabhoadon);
        final RecyclerView recyclerView = view.findViewById(R.id.rv_hoadon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new ScaleInBottomAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(hoadonAdapter);

        fabhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = Fragment_HoaDon.this.getLayoutInflater();
                v = inflater.inflate(R.layout.alert_hoadon, null);
                builder.setView(v);
                edtmahoadon =  v.findViewById(R.id.edtmahoadon);
                edtngaythem =  v.findViewById(R.id.edtngaythem);
                edtmadt = v.findViewById(R.id.edtmadt_them_hoadon);
                edtsoluongmua=v.findViewById(R.id.edtsoluongmua_them_hoadon);
                edtngaythem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chonngay();
                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mahoadon = edtmahoadon.getText().toString();
                        Boolean checkmahd = hoadon_DAO.checkHoadon(mahoadon);
                        if (
                                edtmahoadon.getText().length() == 0 ||
                                edtngaythem.getText().length() == 0||
                                edtmadt.getText().length()==0||
                                edtsoluongmua.getText().length()==0

                        ) {
                            Toast.makeText(getContext(), "Chưa nhập thông tin", Toast.LENGTH_LONG).show();
                        } else if (checkmahd == false) {
                            Toast.makeText(getContext(), "Mã hóa đơn đã tồn tại", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            HoaDon hd = new HoaDon();
                            hd.setMaHD(edtmahoadon.getText().toString());
                            hd.setNgayXuatHD(edtngaythem.getText().toString());
                            hd.setMaDT(edtmadt.getText().toString());
                            hd.setSoluongMua(edtsoluongmua.getText().toString());
                            if (hoadon_DAO.addhoadon(hd) == -1) {
                                Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(getContext(), "Thêm " + edtmahoadon.getText().toString() + " thành công", Toast.LENGTH_SHORT).show();
                            list = hoadon_DAO.getAllHoaDon();
                            hoadonAdapter = new HoaDonAdapter(list, getContext());
                            recyclerView.setAdapter(hoadonAdapter);
                        }
                    }
                });
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    private void chonngay () {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaythem.setText("Ngày " + dayOfMonth + " Tháng " + month + " Năm " + year);
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();

    }

}
