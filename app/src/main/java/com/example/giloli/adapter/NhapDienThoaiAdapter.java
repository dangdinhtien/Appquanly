package com.example.giloli.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giloli.R;
import com.example.giloli.model.DienThoai;

import java.util.ArrayList;


public class NhapDienThoaiAdapter extends BaseAdapter {
    public Context context;

    ArrayList<DienThoai> list;

    public NhapDienThoaiAdapter(ArrayList<DienThoai> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.gridview,null);
        }

        //ánh xạ
        ImageView imgBai3=convertView.findViewById(R.id.imgBai3);
        TextView tvBai3=convertView.findViewById(R.id.tvBai3);

        //gắn dữ liệu vào
        DienThoai w=list.get(position);
        if (position % 3 ==0) {
            imgBai3.setImageResource(R.drawable.images);
        }else if (position % 3 == 1){
            imgBai3.setImageResource(R.drawable.images);
        }else {
            imgBai3.setImageResource(R.drawable.images);
        }
tvBai3.setText(w.getTenDT());



        return convertView;
    }
}
