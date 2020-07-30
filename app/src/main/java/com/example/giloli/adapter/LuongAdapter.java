package com.example.giloli.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giloli.R;
import com.example.giloli.dao.Luong_DAO;
import com.example.giloli.model.Luong;

import java.util.ArrayList;
import java.util.List;


public class LuongAdapter extends BaseAdapter implements Filterable {
    List<Luong> arrluong;
    List<Luong> arrSortluong;
    private Filter luongFilter;
    public Activity context;
    public LayoutInflater inflater;
    Luong_DAO luong_dao;
    String songaylam = "";
    String luongcoban = "";
    public LuongAdapter(Activity context, List<Luong> arrayluong) {
        super();
        this.context = context;
        this.arrluong = arrayluong;
        this.arrSortluong = arrayluong;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        luong_dao = new Luong_DAO(context);
    }
    @Override
    public int getCount() {
        return arrluong.size();
    }
    @Override
    public Object getItem(int position) {
        return arrluong.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        TextView txtSTT;
        TextView txtMaNV;
        TextView txtSongaylam;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_luong_nhanvien, null);
            holder.txtSTT = (TextView)
                    convertView.findViewById(R.id.tvStt);
            holder.txtMaNV = (TextView)
                    convertView.findViewById(R.id.tvManv);
            holder.txtSongaylam= (TextView) convertView.findViewById(R.id.tvSongaylam);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    luong_dao.deleteSachByID(arrluong.get(position).getStt());
                    arrluong.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        Luong _entry = (Luong) arrluong.get(position);
        holder.txtSTT.setText(_entry.getStt());
        holder.txtMaNV.setText("Mã nhân viên: "+_entry.getMaNV());
       luong_dao.tinhluong(_entry.getMaNV(),_entry.getSoNgaylam(),holder.txtSongaylam);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Luong> items){
        this.arrluong = items;
        notifyDataSetChanged();
    }
    public void resetData() {
        arrluong = arrSortluong;
    }
    public Filter getFilter() {
        if (luongFilter == null)
            luongFilter = new CustomFilter();
        return luongFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortluong;
                results.count = arrSortluong.size();
            }
            else {
                List<Luong> lsluong = new ArrayList<Luong>();
                for (Luong p : arrluong) {
                    if
                    (p.getStt().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsluong.add(p);
                }
                results.values = lsluong;
                results.count = lsluong.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrluong = (List<Luong>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
