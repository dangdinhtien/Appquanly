package com.example.duan1_nhom5.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.dao.TheLoai_DAO;
import com.example.duan1_nhom5.model.TheLoaiDT;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    public Context context;
    TheLoai_DAO theLoai_dao;
    ArrayList<TheLoaiDT> danhsachtheloai;
    public  static  View.OnClickListener listener;
    public TheLoaiAdapter( ArrayList<TheLoaiDT> danhsachtheloai,Context context){
    super();
    this.context = context;
    this.danhsachtheloai = danhsachtheloai;
    }
    @NonNull
    @Override
    public TheLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.theloai_1row, viewGroup, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ViewHolder viewHolder, final int position) {
        int i = position + 1;
        viewHolder.txtstt.setText(i + "");
        viewHolder.txttentl.setText(danhsachtheloai.get(position).getTenTL());
        viewHolder.txtvitri.setText(danhsachtheloai.get(position).getViTri());
        viewHolder.txtmaloai.setText(danhsachtheloai.get(position).getMaTL());

        viewHolder.imv_edittheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(R.layout.alert_sua_theloai, null);
                alertdialog.setView(v);

                TheLoaiDT tl = danhsachtheloai.get(position);
                theLoai_dao = new TheLoai_DAO(context);
                final TextView tv_edit_matheloai = v.findViewById(R.id.tvmatheloai);
                final EditText edt_edit_tentheloai = v.findViewById(R.id.edtedittentheloai);
                final EditText edt_edit_vitri = v.findViewById(R.id.edteditvitri);
                // Set lên edittext sẵn khi click chỉnh sửa
                tv_edit_matheloai.setText("Chỉnh sửa mã " + tl.getMaTL());
                edt_edit_tentheloai.setText(tl.getTenTL());
                edt_edit_vitri.setText(tl.getViTri());

                alertdialog.setPositiveButton("Chỉnh sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ten = edt_edit_tentheloai.getText().toString();
                        String vitri = edt_edit_vitri.getText().toString();
                        TheLoaiDT tl1 = danhsachtheloai.get(position);
                        theLoai_dao = new TheLoai_DAO(context);

                        tl1.setTenTL(edt_edit_tentheloai.getText().toString());
                        tl1.setViTri(edt_edit_vitri.getText().toString());

                        if(ten.length() ==0 || vitri.length() == 0){
                            Toast.makeText(context, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            if(theLoai_dao.updateTheLoai(tl1) == -1){
                                Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(context, "Đã cập nhật lại " + danhsachtheloai.get(position).getMaTL(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        danhsachtheloai.clear();
                        danhsachtheloai.addAll(theLoai_dao.getAllTheLoai());
                        TheLoaiAdapter.this.notifyDataSetChanged();
                    }
                });
                alertdialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertdialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhsachtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  txtvitri, txtstt, txtmaloai,txttentl;
        ImageView imv_edittheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv_edittheloai = (ImageView)itemView.findViewById(R.id.imv_edittheloai_1row);
            txtmaloai = (TextView)itemView.findViewById(R.id.matheloai_1row);
            txtstt = (TextView)itemView.findViewById(R.id.stttheloai);
            txttentl =(TextView)itemView.findViewById(R.id.tentheloai_1row);
            txtvitri = (TextView)itemView.findViewById(R.id.vitri_1row);
        }
    }
}

