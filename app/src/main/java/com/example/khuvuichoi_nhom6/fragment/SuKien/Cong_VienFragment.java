package com.example.khuvuichoi_nhom6.fragment.SuKien;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khuvuichoi_nhom6.MainActivity;
import com.example.khuvuichoi_nhom6.R;


public class Cong_VienFragment extends Fragment {

    private TextView tvCongVien_Nuoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cong__vien, container, false);
        tvCongVien_Nuoc = view.findViewById(R.id.textView1);
        tvCongVien_Nuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}