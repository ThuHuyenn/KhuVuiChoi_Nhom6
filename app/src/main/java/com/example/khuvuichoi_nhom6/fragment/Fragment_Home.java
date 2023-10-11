package com.example.khuvuichoi_nhom6.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.khuvuichoi_nhom6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_nhom6.MainActivity;
import com.example.khuvuichoi_nhom6.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class Fragment_Home extends Fragment {

    KhachHang_Dao dao;
    private TextView tv_SLKH, tv_SLLv, tv_SLHD, tv_SLNV;
    private LinearLayout ln_KH, ln_lv, ln_NV, ln_HD;
    private Menu menu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
         ln_KH = view.findViewById(R.id.car_KH);
         ln_lv = view.findViewById(R.id.car_Lv);
         ln_HD = view.findViewById(R.id.car_HD);
         ln_NV = view.findViewById(R.id.car_NV);
         tv_SLKH = view.findViewById(R.id.tv_SLKH);
         tv_SLLv = view.findViewById(R.id.tv_SLLv);
         tv_SLHD = view.findViewById(R.id.tv_SLHD);
         tv_SLNV = view.findViewById(R.id.tv_SLNV);
         menu = view.findViewById(R.id.menu_reset);
         dao = new KhachHang_Dao(getContext());
         dao.open();
         tv_SLNV.setText(dao.getCountNV()+ "");
         tv_SLKH.setText(dao.getCountKH()+ "");
         tv_SLLv.setText(dao.getCountVe()+ "");

          onClick();







       return view;
    }
    private void onClick() {


        ln_lv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Fragment_DangKyVe();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.linerlayout,
                                newFragment, "Vé")
                        .commit();




            }
        });
        ln_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Fragment_QLKhachHang();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.linerlayout,
                                newFragment, "Khách hàng")
                        .commit();
            }
        });
        ln_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Fragment newFragment = new Fragment_HoaDon();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.linerlayout,
                                newFragment, "Hóa Đơn")
                        .commit();
            }
        });

        ln_NV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Fragment_QLNhanVien();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.linerlayout,
                                newFragment, "Nhân viên")
                        .commit();
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);

    }

    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }


}