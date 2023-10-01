package com.example.khuvuichoi_nhom6.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.fragment.Fragment_DangKyVe;
import com.example.khuvuichoi_nhom6.molder.Ve;

import java.util.ArrayList;

public class VeAdapter  extends ArrayAdapter<Ve> {
    private Context context;
Fragment_DangKyVe fragmentDangKyVe;
    private ArrayList<Ve> list;
    TextView tvMaVe,tvStatus;
    ImageView imgDel;

    public VeAdapter(@NonNull Context context, Fragment_DangKyVe fragmentDangKyVe, ArrayList<Ve> list) {
        super(context, 0,list);
        this.context = context;
        this.fragmentDangKyVe = fragmentDangKyVe;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.ve_item,null);
        }
        final Ve item = list.get(position);
        if (item!=null){
            tvMaVe = v.findViewById(R.id.tvMaVe);
            tvMaVe.setText("Mã vé: "+item.getMaVe());

            tvStatus= v.findViewById(R.id.tvStatus);

            if (item.getStatus() == 1) {
                tvStatus.setTextColor(Color.BLUE);
                tvStatus.setText("Còn vé: ");
            } else {
                tvStatus.setTextColor(Color.RED);
                tvStatus.setText("Hết vé: ");
            }

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi function xóa trong ThanhVienFragment
//                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return v;

    }
}
