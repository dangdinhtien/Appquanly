package com.example.giloli;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.giloli.fragment.Fragment_DienThoai;
import com.example.giloli.fragment.NhanVien;
import com.example.giloli.fragment.Them_Fragment;
import com.example.giloli.fragment.TrangchuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navi_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);


        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_main,
                    new TrangchuFragment()).addToBackStack(null).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.trangchu:
                            selectedfragment = new TrangchuFragment();
                            getFragmentManager().beginTransaction();
                            break;
                        case R.id.nhanvien:
                            selectedfragment = new NhanVien();
                            getFragmentManager().beginTransaction();
                            break;
                        case R.id.sanpham:
                            selectedfragment = new Fragment_DienThoai();
                            getFragmentManager().beginTransaction();
                            break;
                        case R.id.danhmuc:
                            selectedfragment = new Them_Fragment();
                            getFragmentManager().beginTransaction();
                            break;
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_main,
                            selectedfragment).addToBackStack(null).commit();

                    return true;
                }
            };

    @Override
    protected void onStart() {
        super.onStart();

    }
}
