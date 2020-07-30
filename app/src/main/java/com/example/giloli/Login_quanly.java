package com.example.giloli;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login_quanly extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin, btnhuy;
    CheckBox chkluuthongtin;
    SharedPreferences luutru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_quanly);
        setTitle("Đăng Nhập");
        edtUsername = (EditText) findViewById(R.id.edtuser);
        edtPassword = (EditText) findViewById(R.id.edtpass);

        chkluuthongtin = (CheckBox) findViewById(R.id.luumatkhau) ;
        btnLogin = (Button) findViewById(R.id.btlogin);
        btnhuy = (Button) findViewById(R.id.btcancel);

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_quanly.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có thật sự muốn thoát khỏi app");
                builder.setMessage("Hãy lựa chọn bên dưới để xát nhận ");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        luutru = getSharedPreferences("myfile", Context.MODE_PRIVATE);

        //nap thong tin len form tu SharedPreferences
        Boolean luuthongtin = luutru.getBoolean("save_information",false);
        if (luuthongtin){
            edtUsername.setText(luutru.getString("username",""));
            edtPassword.setText(luutru.getString("password",""));
            chkluuthongtin.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usename = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (usename.equals("admin")&&password.equals("admin"))
                {
                    Intent intent = new Intent(Login_quanly.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = luutru.edit();
                    if (chkluuthongtin.isChecked())
                    {

                        editor.putString("username", usename);
                        editor.putString("password",password);


                    }
                    editor.putBoolean("save_information",chkluuthongtin.isChecked());
                    editor.commit();
                    finish();

                }
            }
        });

    }


}
