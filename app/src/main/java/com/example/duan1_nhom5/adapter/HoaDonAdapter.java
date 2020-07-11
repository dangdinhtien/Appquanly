package com.example.duan1_nhom5.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.duan1_nhom5.dao.HoaDon_DAO;
import com.example.duan1_nhom5.model.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    ArrayList<HoaDon> danhsachhoadon;
    Context context;
    double thanhtien =0;
    HoaDon_DAO hoaDon_dao;

    public  static  View.OnClickListener listener;
    public HoaDonAdapter(ArrayList<HoaDon> danhsachhoadon, Context context){
        this.danhsachhoadon = danhsachhoadon;
        this.context = context;
    }
    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.hoadon_1row, viewGroup, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder viewHolder, final int position) {
        final int i = position + 1;
        viewHolder.txtstt.setText(i + "");
        viewHolder.txtmahoadon.setText(danhsachhoadon.get(position).getMaHD());
        viewHolder.txtngaythem.setText((CharSequence) danhsachhoadon.get(position).getNgayXuatHD());
        viewHolder.txtmadt_hoadon_them.setText(danhsachhoadon.get(position).getMaDT());

        viewHolder.imv_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(R.layout.alert_hoadonchitiet, null);
                alertdialog.setView(v);


                /////////////////////

                HoaDon hd = danhsachhoadon.get(position);
                hoaDon_dao = new HoaDon_DAO(context);
                final TextView tvmahoadon = v.findViewById(R.id.tv_mahoadon_sua);
                final TextView tvthanhtien = v.findViewById(R.id.tv_thanhtien_sua);
                final EditText edtngaythemdon = v.findViewById(R.id.edteditngaythemdon_sua);
                final EditText edtsoluongdidenthoai = v.findViewById(R.id.edteditsoluongdienthoai_hoadonchitiet_sua);
                final EditText edtmadienthoai = v.findViewById(R.id.edteditmadt_hoadonchitiet_sua);
                // Set lên edittext sẵn khi click chỉnh sửa
                tvmahoadon.setText("Chỉnh sửa mã " + hd.getMaHD());
                edtngaythemdon.setText(hd.getNgayXuatHD());
                edtsoluongdidenthoai.setText(hd.getSoluongMua());
                edtmadienthoai.setText(hd.getMaDT());

                alertdialog.setPositiveButton("Chỉnh sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String  ngaythemdon = edtngaythemdon.getText().toString();
                        String soluongdienthoai = edtsoluongdidenthoai.getText().toString();
                        String madienthoai = edtmadienthoai.getText().toString();
                        HoaDon hd = danhsachhoadon.get(position);
                        hoaDon_dao = new HoaDon_DAO(context);
                        //thanhtien = thanhtien + hd.getSlsachmua() * hd.getMasach().get
                        hd.setNgayXuatHD(edtngaythemdon.getText().toString());
                        hd.setMaDT(edtmadienthoai.getText().toString());
                        hd.setSoluongMua(edtsoluongdidenthoai.getText().toString());
                        if(ngaythemdon.length() ==0 || madienthoai.length() == 0
                                || soluongdienthoai.length() ==0){
                            Toast.makeText(context, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            if(hoaDon_dao.updateHoaDon(hd) == -1){
                                Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(context, "Đã cập nhật lại " + danhsachhoadon.get(position).getMaHD(), Toast.LENGTH_SHORT).show();
                        }
                        danhsachhoadon.clear();
                        danhsachhoadon.addAll(hoaDon_dao.getAllHoaDon());
                        HoaDonAdapter.this.notifyDataSetChanged();
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
        return danhsachhoadon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtngaythem, txtstt, txtmahoadon, txtmadt_hoadon_them;
        ImageView imv_hoadon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmadt_hoadon_them = itemView.findViewById(R.id.txtmadt_them_1row_hoadon);
            txtmahoadon = (TextView)itemView.findViewById(R.id.mahoadon_1row);
            txtstt = (TextView)itemView.findViewById(R.id.stthoadon);
            txtngaythem =(TextView)itemView.findViewById(R.id.ngaythem_1row);
            imv_hoadon = (ImageView)itemView.findViewById(R.id.imv_hoadonct);
        }
    }
}
