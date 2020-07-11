package com.example.duan1_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan1_nhom5.fragment.Fragment_SanPham;
import com.example.duan1_nhom5.fragment.QLNhanVien;
import com.example.duan1_nhom5.fragment.ThongKe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiaoDienQL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Giao Diện Quản Lý");
        setContentView(R.layout.activity_giao_dien_ql);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomquanly);
        bottomNavigationView.setOnNavigationItemSelectedListener(navi);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentquanly,
                    new ThongKe()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navi =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.thongke:
                            fragment = new ThongKe();
                            break;
                        case R.id.qlnv:
                            fragment = new QLNhanVien();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentquanly,fragment).commit();
                    return true;
                }
            };
}
