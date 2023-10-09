package com.example.khuvuichoi_n6;

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

import com.example.khuvuichoi_n6.Fragment.Fragment_DangKyVe;
import com.example.khuvuichoi_n6.Fragment.Fragment_Info;
import com.example.khuvuichoi_n6.Fragment.Fragment_View;
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
                .replace(R.id.flContent, new Fragment_View())
                .commit();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager manager = getSupportFragmentManager();
                if (id == R.id.nav_dangkyve){
                    setTitle("Đăng ký  vé");
                    Fragment_DangKyVe dangKyVe_fra = new Fragment_DangKyVe();
                    manager.beginTransaction().replace(R.id.flContent,dangKyVe_fra)
                            .commit();}
                else if(id == R.id.nav_view){
                    setTitle("Sự Kiện");
                    Fragment_View view_fra = new Fragment_View();
                    manager.beginTransaction().replace(R.id.flContent,view_fra)
                            .commit();
                } else if (id == R.id.nav_Information){
                    setTitle("Thông Tin");
                    Fragment_Info info_fra = new Fragment_Info();
                    manager.beginTransaction().replace(R.id.flContent,info_fra)
                            .commit();
                }else if (id == R.id.nav_Login){
                    setTitle("Login");
                    startActivity(new Intent(MainActivity.this, Login.class));
                }else if (id == R.id.nav_logout) {
                    setTitle("Thoát");
                    finish();
                }
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