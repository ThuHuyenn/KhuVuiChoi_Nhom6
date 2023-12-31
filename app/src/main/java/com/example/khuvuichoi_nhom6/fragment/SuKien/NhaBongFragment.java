package com.example.khuvuichoi_nhom6.fragment.SuKien;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khuvuichoi_nhom6.MainActivity;
import com.example.khuvuichoi_nhom6.R;

public class NhaBongFragment extends Fragment {

  private TextView tvNhaBong;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nha_bong, container, false);
        tvNhaBong = view.findViewById(R.id.textView);
        tvNhaBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}