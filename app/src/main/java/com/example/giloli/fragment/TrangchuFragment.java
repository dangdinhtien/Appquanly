package com.example.giloli.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.giloli.R;
import com.example.giloli.adapter.NhapDienThoaiAdapter;
import com.example.giloli.dao.DienThoai_DAO;
import com.example.giloli.model.DienThoai;

import java.util.ArrayList;

public class TrangchuFragment extends Fragment {
    GridView gv;
    ArrayList<DienThoai> list_dienthoai;
    DienThoai_DAO dienThoai_dao;
    NhapDienThoaiAdapter adapter;
    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Trang chủ");
        viewFlipper = (ViewFlipper) view.findViewById(R.id.ViewFlipper);

        int images[] = {R.drawable.image1, R.drawable.image1, R.drawable.image1, R.drawable.image1
                , R.drawable.image1};
        for (int image : images) {
            setViewFlipper(image);
        }
        gv = view.findViewById(R.id.gvsanpham);
        dienThoai_dao = new DienThoai_DAO(getContext());
        list_dienthoai = new ArrayList<>();
        list_dienthoai = dienThoai_dao.laytatcaDienThoai();
        adapter = new NhapDienThoaiAdapter(list_dienthoai,getContext());
        gv.setAdapter(adapter);
    }
    private void setViewFlipper ( int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getContext(), android.R.anim.fade_in);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.fade_out);

    }
}
