package com.example.khuvuichoi_nhom6;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.khuvuichoi_nhom6.fragment.Fragment_DangKyVe;
import com.example.khuvuichoi_nhom6.fragment.Fragment_HoaDon;
import com.example.khuvuichoi_nhom6.fragment.Fragment_QLKhachHang;
import com.google.android.material.navigation.NavigationView;

public class ManChinh_NhanVien extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_chinh_nhanvien);

        drawerLayout = findViewById(R.id.main_drawlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        //setfram mặc định
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linerlayout, new Fragment_DangKyVe())
                .commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.nav_dangky){
                    fragment = new Fragment_DangKyVe();
                    toolbar.setTitle("Đăng Ký Vé");
                } else if (item.getItemId() == R.id.nav_hoadon) {
                    fragment = new Fragment_HoaDon();
                    toolbar.setTitle("Quản Lý Hóa Đơn");
                }else if (item.getItemId() == R.id.nav_qlKh) {
                    fragment = new Fragment_QLKhachHang();
                    toolbar.setTitle("Quản Lý Khách Hàng");
            }else if (item.getItemId() == R.id.nav_logout) {
                toolbar.setTitle("Logout");
                    startActivity(new Intent(ManChinh_NhanVien.this, Login.class));
                    finish();

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