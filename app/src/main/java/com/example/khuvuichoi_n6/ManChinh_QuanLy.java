package com.example.khuvuichoi_n6;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.khuvuichoi_n6.Fragment.Fragment_HoaDon;
import com.example.khuvuichoi_n6.Fragment.Fragment_QLKhachHang;
import com.example.khuvuichoi_n6.Fragment.Fragment_QuanLyNhaVien;

import com.google.android.material.navigation.NavigationView;

public class ManChinh_QuanLy extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_chinh_quanly);

        drawerLayout = findViewById(R.id.main_drawlayout);
        toolbar = findViewById(R.id.toolbar1);
        navigationView = findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        //setfram mặc định
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linerlayout, new Fragment_QuanLyNhaVien())
                .commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.nav_qlnhanvien){
                    fragment = new Fragment_QuanLyNhaVien();
                    toolbar.setTitle("QL Nhân Viên");
                } else if (item.getItemId() == R.id.nav_qlkhachhang) {
                    fragment = new Fragment_QLKhachHang();
                    toolbar.setTitle("Quản Lý Khách Hàng");
                } else if (item.getItemId() == R.id.nav_hoadon) {
                    fragment = new Fragment_HoaDon();
                    toolbar.setTitle("Hóa Đơn");
                }else if (item.getItemId() == R.id.nav_logout) {
                    startActivity(new Intent(ManChinh_QuanLy.this, Login.class));
                    toolbar.setTitle("Logout");
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.linerlayout,fragment).commit();
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}