package com.example.duan1_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.duan1_nhom5.fragment.Fragment_HoaDon;

public class MainActivity extends AppCompatActivity {
    ImageView imgql,imgnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Trang Chủ");
        setContentView(R.layout.activity_main);
        imgql = findViewById(R.id.imgql);
        imgnv = findViewById(R.id.imgnv);
        imgql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login_quanly.class);
                startActivity(intent);
            }
        });
        imgnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GiaoDienNV.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
