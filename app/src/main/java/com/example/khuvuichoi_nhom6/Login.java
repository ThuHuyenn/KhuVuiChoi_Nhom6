package com.example.khuvuichoi_nhom6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.khuvuichoi_nhom6.Dao.NhanVien_Dao;
import com.example.khuvuichoi_nhom6.Dao.QuanLy_Dao;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private QuanLy_Dao quanLy_dao;
    private NhanVien_Dao nhanVien_dao;
    TextInputEditText ed_User, ed_Pass;
    Button btn_Login;
    CheckBox checkBox;
    TextView tv_Dangky, tv_Quenmk;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_User  = findViewById(R.id.ed_user);
        ed_Pass  = findViewById(R.id.ed_pass);
        btn_Login = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.chkRemember);
        tv_Dangky = findViewById(R.id.tv_dangky);
        tv_Quenmk = findViewById(R.id.tv_quenmk);

        quanLy_dao = new QuanLy_Dao(this);
        nhanVien_dao = new NhanVien_Dao(this);


        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String users = preferences.getString("USERNAME","");
        String passs = preferences.getString("PASSWORD","");
        Boolean rems= preferences.getBoolean("REMEMBER",false);

        ed_User.setText(users);
        ed_Pass.setText(passs);
        checkBox.setChecked(rems);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = ed_User.getText().toString();
                pass = ed_Pass.getText().toString();
                boolean check = quanLy_dao.checkLogin(user,pass);
                boolean check2 = nhanVien_dao.checkLogin_nhanvien(user,pass);
                if(user.isEmpty()||pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    if(check){
                        Toast.makeText(getApplicationContext(), "Đăng nhập  thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(user, pass, checkBox.isChecked());
                        startActivity(new Intent(Login.this, ManChinh_QuanLy.class));

                    }else if(check2) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập  thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(user, pass, checkBox.isChecked());
                        startActivity(new Intent(Login.this, ManChinh_NhanVien.class));
                    }else {
                        Toast.makeText(Login.this, "Tài khoản không tn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        tv_Dangky.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Login.this,DangKy.class));
//            }
//        });
    }
    public  void rememberUser(String u , String p, boolean status){

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if(!status){
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
}