package com.example.khuvuichoi_n6.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.khuvuichoi_n6.Adapter.HoaDon_Adapter;
import com.example.khuvuichoi_n6.Dao.HoaDon_Dao;
import com.example.khuvuichoi_n6.Model.HoaDon;
import com.example.khuvuichoi_n6.R;

import java.util.ArrayList;

public class Fragment_HoaDon extends Fragment {
    private RecyclerView recyclerView;
    private  HoaDon_Dao hoaDon_dao;
    private ArrayList<HoaDon> list;
    private HoaDon_Adapter adapter;

    ListView lv;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don_, container, false);
        lv = view.findViewById(R.id.lvKhachHang);
        hoaDon_dao = new HoaDon_Dao(getActivity());
        capNhatLV();
        return view;
    }
    public void capNhatLV() {
        hoaDon_dao = new HoaDon_Dao(getContext());
        list = (ArrayList<HoaDon>) hoaDon_dao.getAll();
        adapter = new HoaDon_Adapter(getActivity(), this, list);
        lv.setAdapter(adapter);


    }
}