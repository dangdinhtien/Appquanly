package com.example.duan1_nhom5.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.model.TheLoaiDT;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    public SpinnerAdapter( ArrayList<TheLoaiDT> dulieu,Context context){
        this.context = context;
        this.dulieu = dulieu;
    }
    Context context;
    ArrayList<TheLoaiDT> dulieu;

    @Override
    public int getCount() {
        return dulieu.size();
    }

    @Override
    public Object getItem(int position) {
        return dulieu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            // Context phân biệt hoa thường
            LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Ép cả 1 dòng của item_list thành 1 convertView(dạng đóng gói)
            convertView = layoutInflater.inflate(R.layout.spinner_1row,null);
        }
        TextView tvspinnerstt = convertView.findViewById(R.id.spn_stt);
        TextView tvspinnermatheloai = convertView.findViewById(R.id.spn_matheloai);
        TextView tvspinnertentheloai = convertView.findViewById(R.id.spn_tentheloai);

        TheLoaiDT dsl = dulieu.get(position);
        int num = position + 1;
        tvspinnerstt.setText(num + ".");
        tvspinnermatheloai.setText(dsl.getMaTL());
        tvspinnertentheloai.setText(dsl.getTenTL());
        return convertView;

    }
}
