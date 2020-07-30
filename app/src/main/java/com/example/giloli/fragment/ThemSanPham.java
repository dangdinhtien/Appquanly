package com.example.giloli.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giloli.R;
import com.example.giloli.ThemLuongNhanVien;
import com.example.giloli.adapter.DienThoaiAdapter;
import com.example.giloli.adapter.SpinnerAdapter;
import com.example.giloli.adapter.TheLoaiAdapter;
import com.example.giloli.dao.DienThoai_DAO;
import com.example.giloli.dao.Luong_DAO;
import com.example.giloli.dao.NhanVien_DAO;
import com.example.giloli.dao.TheLoai_DAO;
import com.example.giloli.model.DienThoai;
import com.example.giloli.model.Luong;
import com.example.giloli.model.NhanVien;
import com.example.giloli.model.TheLoaiDT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ThemSanPham extends AppCompatActivity {
    DienThoai_DAO dienThoai_dao;
    EditText edtmasodienthoai, edttendienthoai, edtsoluongdienthoai, edtgia;
    Spinner spinner_themdienthoai;
    TheLoai_DAO theloaiDAO;
    List<TheLoaiDT> listtheloai = new ArrayList<>();
    String dienthoai ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dienthoai);

        spinner_themdienthoai = (Spinner) findViewById(R.id.spinner_themdienthoai);
//        try {
//            getTheLoai();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        edttendienthoai = findViewById(R.id.edttendienthoai_them);
        edtmasodienthoai = findViewById(R.id.edtmasodienthoai_them);
        edtsoluongdienthoai = findViewById(R.id.edtsoluongdienthoai_them);
        edtgia = findViewById(R.id.edtgiadienthoai_them);


        spinner_themdienthoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int
                    position, long id) {
                dienthoai =
                        listtheloai.get(spinner_themdienthoai.getSelectedItemPosition()).getMaTL();
                dienThoai_dao = new DienThoai_DAO(ThemSanPham.this);
                dienThoai_dao.laytatcaDienThoai();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edttendienthoai.setText(b.getString("TenDT"));
            edtmasodienthoai.setText(b.getString("MaDT"));
            edtsoluongdienthoai.setText(b.getString("SoLuong"));
            String maTheLoai = b.getString("MaTL");
            edtgia.setText(b.getString("Gia"));

            spinner_themdienthoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

//    public void getTheLoai() throws ParseException {
//        theloaiDAO = new TheLoai_DAO(ThemSanPham.this);
//        listtheloai = TheLoai_DAO.getAllTheLoai();
//        ArrayAdapter<TheLoaiDT> dataAdapter = new ArrayAdapter<TheLoaiDT>(this,
//                android.R.layout.simple_spinner_item, listtheloai);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_themdienthoai.setAdapter(dataAdapter);
//   }
    public void addLuong(View view){
        String maluong= edtmasodienthoai.getText().toString();
        Boolean checksanpham = dienThoai_dao.checkDienThoai(maluong);
        dienThoai_dao = new DienThoai_DAO(ThemSanPham.this);
        DienThoai sach = new
                DienThoai(edtmasodienthoai.getText().toString(),dienthoai,edttendienthoai.getText().toString(),edtsoluongdienthoai.getText().toString(),edtgia.getText().toString());
        try {
            if  (checksanpham == false) {
                Toast.makeText(this, "Thể loại đã tồn tại", Toast.LENGTH_LONG).show();
                return;
            }else if (dienThoai_dao.addDinThoai(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkPositionTheLoai(String strTheLoai){
        for (int i = 0; i <listtheloai.size(); i++){
            if (strTheLoai.equals(listtheloai.get(i).getMaTL())){
                return i;
            }
        }
        return 0;
    }
}
