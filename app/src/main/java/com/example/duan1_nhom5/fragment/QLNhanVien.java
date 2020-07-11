package com.example.duan1_nhom5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.duan1_nhom5.R;
import com.example.duan1_nhom5.adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class QLNhanVien extends Fragment {
    ViewPager viewPager2;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qlnhanvien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewpager);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        sectionsPagerAdapter.addFragment(new NhanVien());
        sectionsPagerAdapter.addFragment(new NVChuyenCan());
        sectionsPagerAdapter.addFragment(new LuongNhanVien());
        viewPager2.setAdapter(sectionsPagerAdapter);
        //tab layout
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager2);
        tabLayout.getTabAt(0).setText("Nhân Viên");
        tabLayout.getTabAt(1).setText("Nhân Viên Chuyên Cần");
        tabLayout.getTabAt(2).setText("Lương Nhân Viên");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
