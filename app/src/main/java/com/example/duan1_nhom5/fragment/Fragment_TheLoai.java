package com.example.duan1_nhom5.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.adapter.TheLoaiAdapter;
import com.example.duan1_nhom5.dao.TheLoai_DAO;
import com.example.duan1_nhom5.model.TheLoaiDT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class Fragment_TheLoai extends Fragment {
    TheLoai_DAO theloaiDAO;
    ArrayList<TheLoaiDT> list;
    TheLoaiAdapter theloaiAdapter;
    FloatingActionButton fabtheloai;
    public Fragment_TheLoai(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_theloai,container,false);

        list = new ArrayList<>();
        theloaiDAO = new TheLoai_DAO(getActivity());
        list = theloaiDAO.getAllTheLoai();
        theloaiAdapter = new TheLoaiAdapter(list,getContext());
        fabtheloai = view.findViewById(R.id.fabtheloai);
        final RecyclerView recyclerView = view.findViewById(R.id.rv_theloai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new ScaleInBottomAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(theloaiAdapter);

        fabtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = Fragment_TheLoai.this.getLayoutInflater();
                v = inflater.inflate(R.layout.alert_theloai, null);
                builder.setView(v);
                final EditText edttentheloai = v.findViewById(R.id.edttentheloai);
                final EditText edtmatheloai = v.findViewById(R.id.edtmatheloai);
                final  EditText edtvitri = v.findViewById(R.id.edtvitri);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (edtvitri.getText().length() == 0 ||
                                edtmatheloai.getText().length() == 0) {
                            Toast.makeText(getContext(), "Chưa nhập thông tin", Toast.LENGTH_LONG).show();
                        } else {
                            TheLoaiDT tl = new TheLoaiDT();
                            tl.setMaTL(edtmatheloai.getText().toString());
                             tl.setTenTL(edttentheloai.getText().toString());
                            tl.setViTri(edtvitri.getText().toString());
                            if (theloaiDAO.addTheLoai(tl) == -1) {
                                Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(getContext(), "Thêm " + edtmatheloai.getText().toString() + " thành công", Toast.LENGTH_SHORT).show();
                            list = theloaiDAO.getAllTheLoai();
                            theloaiAdapter = new TheLoaiAdapter(list, getContext());
                            recyclerView.setAdapter(theloaiAdapter);
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


}
