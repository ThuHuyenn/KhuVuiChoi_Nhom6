package com.example.khuvuichoi_nhom6.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.khuvuichoi_nhom6.Adapter.HoaDonAdapter;
import com.example.khuvuichoi_nhom6.Dao.HoaDon_Dao;
import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.molder.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Fragment_HoaDon extends Fragment {


    RecyclerView rcv;
    HoaDon_Dao dao;
    ArrayList<HoaDon> list =new ArrayList<>();
    HoaDonAdapter adapter;
    TextView tvDoanhThu, tvTheoLuot, tvDKve, tvTuNgay, tvDenNgay;
    Button btnDoanhThu;
    int mY, mM, mD;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hoa__don, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_thongkeQL);
        tvDoanhThu = view.findViewById(R.id.tv_doanhThu);
        tvTheoLuot = view.findViewById(R.id.tv_theoLuot);
        tvTuNgay = view.findViewById(R.id.tv_tuNgay);
        tvDenNgay = view.findViewById(R.id.tv_denNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new HoaDon_Dao(getContext());
        list = dao.getDs();
        adapter = new HoaDonAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        tvTheoLuot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheoLuot.setTextColor(Color.BLUE);
                tvDKve.setTextColor(Color.WHITE);
                list = dao.getDs();
                adapter = new HoaDonAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });

        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mTuNgay, mY, mM, mD);
                d.show();
            }
        });
        Date objDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new DateFormat();
        String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
        tvDenNgay.setText(chuoi_ngay_thang_nam);
        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mDenNgay, mY, mM, mD);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = tvTuNgay.getText().toString();
                String denNgay = tvDenNgay.getText().toString();
                int dtHD = dao.DoanhThu(tuNgay, denNgay);
                int doanhThu = dtHD ;
                tvDoanhThu.setText("Doanh thu: "+doanhThu+"VNĐ");
                tvTheoLuot.setText("Theo lượt: "+dtHD+"VND");
            }
        });
        int dtTheoLuot = 0;
        int dtDKve = 0;
        for(int i=0; i<list.size(); i++){
            dtTheoLuot += list.get(i).getGiatien();
        }

        int doanhThu = dtTheoLuot+dtDKve;
        tvDoanhThu.setText("Doanh thu: "+doanhThu+"VND");
        tvTheoLuot.setText("Theo lượt: "+dtTheoLuot+"VND");
        tvDKve.setText("Đăng ký vé: "+dtDKve+"VND");
    }
    DatePickerDialog.OnDateSetListener mTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            tvTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            tvDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}
