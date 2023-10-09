package com.example.khuvuichoi_n6.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.khuvuichoi_n6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_n6.Dao.NhanVien_Dao;
import com.example.khuvuichoi_n6.Fragment.Fragment_QuanLyNhaVien;
import com.example.khuvuichoi_n6.Model.KhachHang;
import com.example.khuvuichoi_n6.Model.NhanVien;
import com.example.khuvuichoi_n6.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NhanVien_Adapter extends ArrayAdapter<NhanVien> {
    TextView lv_id, lv_ten, lv_pass,lv_sdt,lv_status, lv_diachi,lv_gmail;
    private Context context;
    private Fragment_QuanLyNhaVien fragment_quanLyNhaVien;
    private ArrayList<NhanVien> list;
    private NhanVien_Dao nhanVien_dao;
    private ImageView imgDel,imgUpdate;
    TextInputEditText up_id, up_ten,up_pass, up_sdt, up_diachi,up_email;
    CheckBox chkSttNV;
    private Dialog dialog;
    Button btnSave ,btnCancel;

    public NhanVien_Adapter( Context context, Fragment_QuanLyNhaVien fragment_quanLyNhaVien,ArrayList<NhanVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment_quanLyNhaVien = fragment_quanLyNhaVien;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_nhanvien,null);
        }
        final NhanVien item = list.get(position);

        if (item!=null){
            lv_id = v.findViewById(R.id.lv_id);
            lv_id.setText(" "+item.getId());
            lv_ten = v.findViewById(R.id.lv_ten);
            lv_ten.setText(" "+item.getTendangnhap());
            lv_pass = v.findViewById(R.id.lv_pass);
            lv_pass.setText(" "+item.getMatkhau());
            lv_sdt = v.findViewById(R.id.lv_sdt);
            lv_sdt.setText(" "+item.getSdt());
            lv_diachi = v.findViewById(R.id.lv_diachi);
            lv_diachi.setText(" "+item.getDiachi());
            lv_gmail = v.findViewById(R.id.lv_email);
            lv_gmail.setText(" "+item.getEmail());
            lv_status = v.findViewById(R.id.id_status);
            lv_status.setText(" Đang hoạt động ");
            imgDel = v.findViewById(R.id.btn_delete);
            imgUpdate = v.findViewById(R.id.btn_edit);
        }

        if(item.getStatus()==0){
            lv_status.setTextColor(Color.BLUE);
            lv_status.setText(" Ngưng hoạt động");
        }else{
            lv_status.setTextColor(Color.RED);
            lv_status.setText("Đang hoạt động");
        }

        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//             UpdateKH(getContext() , item);
                openDialog(context,item);
            }

        });
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVien_dao = new NhanVien_Dao(context);
                fragment_quanLyNhaVien.xoa(item);
            }
        });

        return v;

    }
    public void capNhatLV () {
        nhanVien_dao = new NhanVien_Dao(getContext());
        list = (ArrayList<NhanVien>) nhanVien_dao.getAll();
        notifyDataSetChanged();

    }

    protected void openDialog(final Context context , NhanVien item) {


        dialog = new Dialog(context);
        dialog.setContentView(R.layout.update_nhanvien);
        up_id = dialog.findViewById(R.id.up_ma);
        up_ten = dialog.findViewById(R.id.up_ten);
        up_pass = dialog.findViewById(R.id.up_mathau);
        up_sdt = dialog.findViewById(R.id.up_sdt);
        up_diachi = dialog.findViewById(R.id.up_diachi);
        up_email = dialog.findViewById(R.id.up_email);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        chkSttNV = dialog.findViewById(R.id.chk_nv);

//        item = new KhachHang();
//        edMaKH.setText(item.getMaKH());
        up_id.setText(String.valueOf(item.getId()));
        up_ten.setText(item.getTendangnhap());
        up_pass.setText(item.getMatkhau());
        up_sdt.setText(String.valueOf(item.getSdt()));
        up_diachi.setText(item.getDiachi());
        up_email.setText(item.getEmail());
        chkSttNV.setText(String.valueOf(item.getStatus()));

        if(item.getStatus()==1){
            chkSttNV.setChecked(true);
        }else {
            chkSttNV.setChecked(false);
        }



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nhanVien_dao = new NhanVien_Dao(getContext());
//                item = new KhachHang();
//                item = dao.getMaKH(maKH);
                item.setTendangnhap(up_ten.getText().toString());
                item.setMatkhau(up_pass.getText().toString());
                item.setSdt(Integer.parseInt(up_sdt.getText().toString()));
                item.setDiachi(up_diachi.getText().toString());
                item.setEmail(up_email.getText().toString());
                item.setStatus(Integer.parseInt(chkSttNV.getText().toString()));
                if(chkSttNV.isChecked()){
                    item.setStatus(1);
                if (nhanVien_dao.updateNv(item)>0){
//                        Bundle bundle = new Bundle();
//                        bundle.putString("maKH", String.valueOf(item.getMaKH()));
                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                }else {
                    Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                }}
                else{
                    item.setStatus(0);
                    if (nhanVien_dao.updateNv(item)>0){
//                        Bundle bundle = new Bundle();
//                        bundle.putString("maKH", String.valueOf(item.getMaKH()));
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        capNhatLV();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }}

//                    capNhatLV();
                dialog.dismiss();
            }
//
        });
        dialog.show();
    }
}


