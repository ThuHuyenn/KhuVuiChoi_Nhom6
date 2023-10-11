package com.example.khuvuichoi_nhom6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.khuvuichoi_nhom6.fragment.Fragment_QuyDinh;
import com.example.khuvuichoi_nhom6.fragment.InformationFragment;
import com.example.khuvuichoi_nhom6.fragment.View_ventFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        // set toolbar thay th cho actionbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView nv = findViewById(R.id.nv_View);
        // show
        mHeaderView = nv.getHeaderView(0);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, new View_ventFragment())
                .commit();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager manager = getSupportFragmentManager();
                if (id == R.id.nav_View_Vent){
                    setTitle("Sự Kiện");
                    View_ventFragment view_ventFragment = new View_ventFragment();
                    manager.beginTransaction().replace(R.id.flContent,view_ventFragment)
                            .commit();
                } else if (id == R.id.nav_Information){
                    setTitle("Thông Tin");
                    InformationFragment fragment = new InformationFragment();
                    manager.beginTransaction().replace(R.id.flContent,fragment)
                            .commit();
                }else if (id == R.id.nav_Quydinh){
                    setTitle("Quy định");
                    Fragment_QuyDinh ve = new Fragment_QuyDinh();
                    manager.beginTransaction().replace(R.id.flContent,ve)
                            .commit();
                }else if (id == R.id.nav_Login){
                    setTitle("Login");
                    startActivity(new Intent(MainActivity.this, Login.class));

                }else if (id == R.id.nav_logout) {
                    setTitle("Thoát");
                    finish();
                }
//        }else if (id == R.id.nav_NhanVien){
//            setTitle("Nhân Viên ");
//            FragmentNhan_Vien nhanVien = new FragmentNhan_Vien();
//            manager.beginTransaction().replace(R.id.flContent,nhanVien)
//                    .commit();
//        }else if (id == R.id.nav_View_Vent){
//            setTitle("Sự Kiện");
//            View_ventFragment view_ventFragment = new View_ventFragment();
//            manager.beginTransaction().replace(R.id.flContent,view_ventFragment)
//                    .commit();
//        }else if (id == R.id.nav_doanhThu){
//            setTitle("Doanh Thu");
//           FragmentDoanh_Thu doanhThu = new FragmentDoanh_Thu();
//            manager.beginTransaction().replace(R.id.flContent,doanhThu)
//                    .commit();
//        }
                drawer.closeDrawers();
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }


}