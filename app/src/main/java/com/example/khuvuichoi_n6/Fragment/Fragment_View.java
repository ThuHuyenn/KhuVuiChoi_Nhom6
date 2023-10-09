package com.example.khuvuichoi_n6.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.khuvuichoi_n6.Fragment.SuKien.Bat_Lo_xoFragment;
import com.example.khuvuichoi_n6.Fragment.SuKien.Cong_VienFragment;
import com.example.khuvuichoi_n6.Fragment.SuKien.NhaBongFragment;
import com.example.khuvuichoi_n6.Fragment.SuKien.NhaMaFragment;
import com.example.khuvuichoi_n6.Fragment.SuKien.Vong_QuayFragment;
import com.example.khuvuichoi_n6.R;

public class Fragment_View extends Fragment {

    private LinearLayout lin_1,lin_2,lin_3,lin_4,lin_5;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_, container, false);
        lin_1 = view.findViewById(R.id.lin);
        lin_2 = view.findViewById(R.id.lin2);
        lin_3 = view.findViewById(R.id.lin3);
        lin_4 = view.findViewById(R.id.lin4);
        lin_5 = view.findViewById(R.id.lin5);
        onClick();

        return view;
    }
    private void onClick(){
        lin_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new NhaBongFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,
                                newFragment, "...")
                        .commit();
            }
        });
        lin_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Bat_Lo_xoFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,
                                newFragment, "...")
                        .commit();

            }
        });
        lin_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Vong_QuayFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,
                                newFragment, "...")
                        .commit();
            }
        });
        lin_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Cong_VienFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,
                                newFragment, "...")
                        .commit();
            }
        });
        lin_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new NhaMaFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,
                                newFragment, "...")
                        .commit();
            }
        });

    }
}