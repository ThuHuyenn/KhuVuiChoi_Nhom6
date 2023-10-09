package com.example.khuvuichoi_n6.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khuvuichoi_n6.Dao.HoaDon_Dao;
import com.example.khuvuichoi_n6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_n6.Fragment.Fragment_HoaDon;
import com.example.khuvuichoi_n6.Fragment.Fragment_QLKhachHang;
import com.example.khuvuichoi_n6.Model.HoaDon;
import com.example.khuvuichoi_n6.Model.KhachHang;
import com.example.khuvuichoi_n6.R;

import java.util.ArrayList;

public class HoaDon_Adapter extends ArrayAdapter<HoaDon> {
   private Context context;
    TextView tv_tenkh, tv_giave, tv_time, tv_tt;

    private Fragment_HoaDon fragment_hoaDon;
    HoaDon_Dao hoaDon_dao;
    private ArrayList<HoaDon> list;

    public HoaDon_Adapter(Context context, Fragment_HoaDon fragment_hoaDon, ArrayList<HoaDon> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment_hoaDon = fragment_hoaDon;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_hoadon,null);
        }
        final  HoaDon item = list.get(position);
        if (item!=null){
            tv_tenkh = v.findViewById(R.id.tv_tenKH);
            tv_tenkh.setText(" "+item.getMaKH());
            tv_giave = v.findViewById(R.id.txt_giatien);
            tv_giave.setText(" "+item.getGiave());
            tv_time = v.findViewById(R.id.tv_time);
            tv_time.setText(" "+item.getTime());
            tv_tt = v.findViewById(R.id.tv_httt);
            tv_tt.setText(" "+item.getThanhtoan());
        }
        return v;

    }
        public void capNhatLV () {
            hoaDon_dao = new HoaDon_Dao(getContext());
            list = (ArrayList<HoaDon>) hoaDon_dao.getAll();
            notifyDataSetChanged();

        }

    }
