package com.example.duan1_nhom5.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.dao.DienThoai_DAO;
import com.example.duan1_nhom5.model.DienThoai;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.ViewHolder> {
    Context context;
    DienThoai_DAO dienThoai_dao;
    TextView tvmadt, tvtldt;
    EditText  edt_ten_sua_dienthoai, edt_soluong_sua_dienthoai, edt_gia_sua_dienthoai;
    ArrayList<DienThoai> danhsachdienthoai;
    public DienThoaiAdapter( ArrayList<DienThoai> danhsachdienthoai,Context context){

        this.danhsachdienthoai = danhsachdienthoai;
        this.context = context;
    }
    @NonNull
    @Override
    public DienThoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.dienthoai_1row, viewGroup, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull DienThoaiAdapter.ViewHolder viewHolder, final int position) {
        int i = position + 1;
        viewHolder.txtsttdienthoai.setText(i + "");
        viewHolder.txtmadt.setText(danhsachdienthoai.get(position).getMaDT());
        viewHolder.txtdienthoai.setText(danhsachdienthoai.get(position).getTenDT());
        viewHolder.txtsoluong.setText(danhsachdienthoai.get(position).getSoLuong());
        viewHolder.txtgia.setText(danhsachdienthoai.get(position).getGia());

            //xóa điện thoại
       viewHolder.imv_deletedienthoai.setOnClickListener(new View.OnClickListener() {
           /* @Override
        public void onClick(View v) {
              Snackbar.make(((Activity)context).findViewById(R.id.rv_dienthoai),"Xoa Dienthoai"+danhsachdienthoai.get(position).getTenDT(),3500)
                   .setActionTextColor(Color.RED)
                      .setAction("Dong y", new View.OnClickListener() {}});.show();*/
                           @Override
                           public void onClick(View v) {
                                DienThoai dienThoai=danhsachdienthoai.get(position);
                                dienThoai_dao = new DienThoai_DAO(context);
                                if (dienThoai_dao.deleteDienThoai(dienThoai)==-1){
                                    Toast.makeText(context, "Xoa Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                               Toast.makeText(context, "Da Xoa"+danhsachdienthoai.get(position).getTenDT(), Toast.LENGTH_SHORT).show();
                                danhsachdienthoai.clear();
                                danhsachdienthoai.addAll(dienThoai_dao.laytatcaDienThoai());
                                DienThoaiAdapter.this.notifyDataSetChanged();

                           }
                       });
                //sua dien thoai
        viewHolder.imv_editdienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(R.layout.alert_sua_dienthoai, null);
                alertdialog.setView(v);

                DienThoai dienThoai2 = danhsachdienthoai.get(position);
                dienThoai_dao = new DienThoai_DAO(context);
                 tvmadt = v.findViewById(R.id.edt_sua_madt_dienthoai);
                 tvtldt = v.findViewById(R.id.edt_sua_theloai_dienthoai);
                 edt_ten_sua_dienthoai = v.findViewById(R.id.edt_sua_ten_dienthoai);
                 edt_soluong_sua_dienthoai = v.findViewById(R.id.edt_sua_soluong_dienthoai);
                 edt_gia_sua_dienthoai = v.findViewById(R.id.edt_sua_gia_dienthoai);

                // Set lên edittext sẵn khi click chỉnh sửa
                tvmadt.setText("Ma điện thoại " + dienThoai2.getMaDT());
                tvtldt.setText("Thể loại: " + dienThoai2.getMaTL());
                edt_ten_sua_dienthoai.setText(dienThoai2.getTenDT());

                alertdialog.setPositiveButton("Chỉnh sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tendt = edt_ten_sua_dienthoai.getText().toString();
                        String soluong = edt_soluong_sua_dienthoai.getText().toString();
                        String gia = edt_gia_sua_dienthoai.getText().toString();

                        DienThoai dienThoai1 = danhsachdienthoai.get(position);
                        dienThoai_dao = new DienThoai_DAO(context);
                        dienThoai1.setTenDT(edt_ten_sua_dienthoai.getText().toString());
                        dienThoai1.setSoLuong(edt_soluong_sua_dienthoai.getText().toString());
                        dienThoai1.setGia(edt_gia_sua_dienthoai.getText().toString());

                        if(tendt.length() ==0 || soluong.length() == 0 || gia.length() == 0 ){
                            Toast.makeText(context, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            if(dienThoai_dao.updateDienThoai(dienThoai1) == -1){
                                Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(context, "Đã cập nhật lại " + danhsachdienthoai.get(position).getTenDT(), Toast.LENGTH_SHORT).show();
                        }
                        danhsachdienthoai.clear();
                        danhsachdienthoai.addAll(dienThoai_dao.laytatcaDienThoai());
                        DienThoaiAdapter.this.notifyDataSetChanged();
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
        return danhsachdienthoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtdienthoai, txtsoluong, txtgia, txtsttdienthoai, txtmadt;
        ImageView imv_editdienthoai, imv_deletedienthoai,imv_dt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv_editdienthoai = (ImageView)itemView.findViewById(R.id.imv_editdienthoai_sua);
            imv_deletedienthoai = (ImageView)itemView.findViewById(R.id.imv_deletedienthoai_sua);
imv_dt = itemView.findViewById(R.id.imv_dt);
            txtmadt = (TextView)itemView.findViewById(R.id.madienthoai_1row);
            txtdienthoai = (TextView)itemView.findViewById(R.id.tendienthoai_1row);
            txtsttdienthoai = (TextView)itemView.findViewById(R.id.sttdienthoai_sua) ;
            txtsoluong =(TextView)itemView.findViewById(R.id.soluongdienthoai_1row_sua);
            txtgia = (TextView)itemView.findViewById(R.id.giadienthoai_1row_sua);
        }
    }
}
