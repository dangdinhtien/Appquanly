package com.example.duan1_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.duan1_nhom5.fragment.Fragment_HoaDon;
import com.example.duan1_nhom5.fragment.Fragment_SanPham;
import com.example.duan1_nhom5.fragment.HangKho;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiaoDienNV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_nv);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomquannv);
        bottomNavigationView.setOnNavigationItemSelectedListener(navi);

    /*    // Mặc đinh mới vào là Fragment Thống kê
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ThongKe()).commit();
            navigationView.setCheckedItem(R.id.thongke);
        }*/
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navi =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.hoadon:
                            fragment = new Fragment_HoaDon();
                            break;
                        case R.id.sp:
                            fragment = new Fragment_SanPham();
                            break;
                        case R.id.kho:
                            fragment = new HangKho();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentnv,fragment).commit();
                    return true;
                }
            };
    }

