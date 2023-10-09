package com.example.khuvuichoi_n6.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.khuvuichoi_n6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_n6.Fragment.Fragment_QLKhachHang;
import com.example.khuvuichoi_n6.Model.KhachHang;
import com.example.khuvuichoi_n6.R;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Fragment_QLKhachHang fragment_khachHang;
    private Context context;
    private ArrayList<KhachHang> list;
    private TextView tvMaKH,tvHotenKH,tvSdtKH,tvDiaChi,tvGmailKH;
    private ImageView imgDel,imgUpdate;
    private KhachHang_Dao dao;
    private Dialog dialog;
    private KhachHangAdapter adapter;
    private EditText edMaKH, edHoTenKH, edDienThoaiKH, edDiaChiKH, edGmailKH;
    Button btnSave, btnCancel;
    KhachHang item;
    private String maKH;
    public KhachHangAdapter(@NonNull Context context, Fragment_QLKhachHang fragment_khachHang, ArrayList<KhachHang> list) {
        super(context, 0,list);
        this.fragment_khachHang = fragment_khachHang;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_item_khach_hang,null);
        }
        final  KhachHang item = list.get(position);
        if (item!=null){
            tvMaKH = v.findViewById(R.id.tvMaKH);
            tvMaKH.setText("Mã khách hàng: "+item.getMaKH());
            tvHotenKH = v.findViewById(R.id.tvHoTenKH);
            tvHotenKH.setText("Họ tên: "+item.getHoTen());
            tvSdtKH = v.findViewById(R.id.tvDienThoaiKH);
            tvSdtKH.setText("SdT: "+item.getDienThoai());
            tvDiaChi = v.findViewById(R.id.tvDiaChiKH);
            tvDiaChi.setText("Địa chỉ: "+item.getDiaChi());
            tvGmailKH = v.findViewById(R.id.tvGmailKH);
            tvGmailKH.setText("Gmail: "+item.getGmail());
            imgDel = v.findViewById(R.id.imgDelKH);
            imgUpdate = v.findViewById(R.id.imgUpdate);



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
                dao = new KhachHang_Dao(context);
                fragment_khachHang.xoa(item);
            }
});

        return v;

    }

    public void UpdateKH(Context context , KhachHang item){
        // Sử dụng AlertDialog
        dao = new KhachHang_Dao(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update");
        builder.setMessage("Bạn có chắc chắn muốn Update  không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                 openDialog(getContext() , item);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context , KhachHang item) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.khachhang_dialog);
        edMaKH = dialog.findViewById(R.id.edMaKhachHang);
        edHoTenKH = dialog.findViewById(R.id.edHoTenKH);
        edDienThoaiKH = dialog.findViewById(R.id.edSoDTKH);
        edDiaChiKH = dialog.findViewById(R.id.edDiachiKH);
        edGmailKH = dialog.findViewById(R.id.edGmail);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
//        item = new KhachHang();
//        edMaKH.setText(item.getMaKH());
            edHoTenKH.setText(item.getHoTen());
            edDienThoaiKH.setText(item.getDienThoai());
            edDiaChiKH.setText(item.getDiaChi());
            edGmailKH.setText(item.getGmail());


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dao = new KhachHang_Dao(getContext());
//                item = new KhachHang();
//                item = dao.getMaKH(maKH);

                item.setHoTen(edHoTenKH.getText().toString());
                item.setDienThoai(edDienThoaiKH.getText().toString());
                item.setDiaChi(edDiaChiKH.getText().toString());
                item.setGmail(edGmailKH.getText().toString());



                    if (dao.updateKH(item)>0){
//                        Bundle bundle = new Bundle();
//                        bundle.putString("maKH", String.valueOf(item.getMaKH()));
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        capNhatLV();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();

                    }
//                    capNhatLV();
                dialog.dismiss();

                }
//

        });
        dialog.show();


    }

    public void capNhatLV() {
        dao = new KhachHang_Dao(getContext());
        list = (ArrayList<KhachHang>) dao.getAll();
        notifyDataSetChanged();

    }



}
