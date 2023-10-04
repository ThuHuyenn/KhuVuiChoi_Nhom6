package com.example.khuvuichoi_nhom6.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khuvuichoi_nhom6.Dao.HoaDon_Dao;
import com.example.khuvuichoi_nhom6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_nhom6.Dao.NhanVien_Dao;
import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.molder.HoaDon;
import com.example.khuvuichoi_nhom6.molder.KhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.Viewhodel> {
    private Context context;
    private ArrayList<HoaDon> list;
    private HoaDon_Dao hoaDon_dao;
    KhachHang_Dao khachHang_dao;
    NhanVien_Dao nhanVien_dao;
    HoaDon_Dao dao;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        dao = new HoaDon_Dao(context);
        khachHang_dao = new KhachHang_Dao(context);
        nhanVien_dao = new NhanVien_Dao(context);
    }



    @NonNull
    @Override
    public Viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();//hiển thị list
        View view = inflater.inflate(R.layout.list_item_hoadon,parent,false);//hiển thị list
        return new Viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodel holder, int position) {
        holder.tv_tenkh.setText(list.get(position).getMaKH());
        holder.tv_giave.setText(list.get(position).getGiatien());
        holder.tv_time.setText(list.get(position).getTime());//hiển thị list
        holder.tv_httt.setText(String.valueOf(list.get(position).getThanhtoan()));//hiển thị list

        HoaDon hd = list.get(position);
        int idKH = hd.getMaKH();
        KhachHang kh = khachHang_dao.getMaKH(String.valueOf(idKH));
        if(hd.getStatusHD()==1){
            holder.tv_status.setTextColor(Color.GREEN);
            holder.tv_status.setText("Đã thanh toán");
        }else{
            holder.tv_status.setTextColor(Color.RED);
            holder.tv_status.setText("Chưa thanh toán");
        }
        holder.tv_tenkh.setText(kh.getHoTen());
        if(hd.getStatusHD()==0){
            holder.tv_time.setText(simpleDateFormat.format(hd.getTime()));
        }
        if(hd.getThanhtoan()==0){
            holder.tv_httt.setText("Chuyển khoản");
        }else {
            holder.tv_httt.setText("Tiền mặt");
        }
        holder.tv_giave.setText("Hóa đơn: "+hd.getGiatien()+"VND");

        //xóa
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdle(list.get(holder.getAdapterPosition()).getTime(),
                        list.get(holder.getAdapterPosition()).getId_hoadon());//phải là kiểu int
            }
        });
        //update

    }
    //dành cho xóa
    private void showdle(String id_hoadon, int id ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setIcon(R.drawable.ic_warn);
        builder.setMessage("Bạn có muốn xóa :\"" + id_hoadon +"\" không?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = hoaDon_dao.delete(id);
                if(check){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = hoaDon_dao.getDs();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhodel extends RecyclerView.ViewHolder{
        //hiển thị list
        TextView tv_tenkh, tv_giave,tv_time,tv_httt,tv_status;
        ImageView btn_delete, btn_edit;
        public Viewhodel(@NonNull View itemView) {
            super(itemView);
            tv_tenkh = itemView.findViewById(R.id.tv_tenKH);
            tv_giave = itemView.findViewById(R.id.txt_giatien);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_httt = itemView.findViewById(R.id.tv_httt);
            tv_status = itemView.findViewById(R.id.tv_status);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);

        }
    }

    //hàm sửa

//    private void opendialogsua( HoaDon dv) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        //tạo view gán layout
//        LayoutInflater inflater=((Activity) context).getLayoutInflater();
//        View view=inflater.inflate(R.layout.item_update,null);
//        builder.setView(view);//gán view vào hôp thoại
//        Dialog dialog=builder.create();//tạo hộp thoại
//        dialog.show();
//
//        //ánh xạ các thành phần widget
//        EditText ed_tenkh=view.findViewById(R.id.tv_tenkh);
//        EditText ed_giatien=view.findViewById(R.id.tv_giave);
//        EditText ed_time=view.findViewById(R.id.tv_time);
//        EditText ed_httt=view.findViewById(R.id.tv_httt);
//        Button btn_sua=view.findViewById(R.id.btn_update);
//        Button btn_huy=view.findViewById(R.id.btn_huy);
//
//
//        //gán dữ liệu lên các widget
//        ed_tenkh.setText(dv.getMaKH());
//        ed_giatien.setText(dv.getGiatien());
//        ed_time.setText(String.valueOf(dv.getTime()));
//        ed_httt.setText(String.valueOf(dv.getThanhtoan()));
//        ed_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar lich=Calendar.getInstance();//tạo đối tượng để lấy ngày giờ hiện tại
//                int year=lich.get(Calendar.YEAR);
//                int month=lich.get(Calendar.MONTH);
//                int day=lich.get(Calendar.DAY_OF_MONTH);
//                //Tạo đối tượng DatePickerDialog và show nó
//                DatePickerDialog datedg=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        ed_time.setText(String.format("%d/%d/%d",dayOfMonth,month,year));
//                    }
//                },year,month,day);
//                datedg.show();
//            }
//        });
//
//        ed_giatien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
//                builder1.setTitle("Giá vé");
//                String loai[] = {"50.000"};
//                builder1.setItems(loai, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ed_giatien.setText(loai[which]);
//                    }
//                });
//                AlertDialog alertDialog = builder1.create();
//                alertDialog.show();
//            }
//        });
//
//        btn_sua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TextUtils.isEmpty(edtmadv.getText().toString())||TextUtils.isEmpty(edtnoidung.getText().toString())||TextUtils.isEmpty(edtngay.getText().toString())){
//                    Toast.makeText(context, "Yêu cầu nhập đầy ddur", Toast.LENGTH_SHORT).show();
//                }else{
//                    try {
//                        dv.setMamh(edtmadv.getText().toString());
//                        dv.setTenmh(edtnoidung.getText().toString());
//                        dv.setSotiet(Integer.parseInt(edtngay.getText().toString()));
//
//                        if (monhoc_dao.update(dv)){
//                            list.clear();
//                            list.addAll(monhoc_dao.getDs());
//                            notifyDataSetChanged();
//                            dialog.dismiss();
//                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }catch (Exception e){
//                        Toast.makeText(context, "Tiền phải là số", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            }
//        });
//    }
}

