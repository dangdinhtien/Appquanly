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

public class HangKho extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hangkho,container,false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hang kho");
        viewPager = view.findViewById(R.id.viewpager2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        sectionsPagerAdapter.addFragment(new Fragment_TheLoai());
        sectionsPagerAdapter.addFragment(new Fragment_DienThoai());
        viewPager.setAdapter(sectionsPagerAdapter);
        //tab layout
        tabLayout = view.findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Loại Điện Thoại");
        tabLayout.getTabAt(1).setText("Điện Thoại");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
