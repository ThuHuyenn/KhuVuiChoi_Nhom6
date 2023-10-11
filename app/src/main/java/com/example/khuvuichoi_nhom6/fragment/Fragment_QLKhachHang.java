
package com.example.khuvuichoi_nhom6.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.khuvuichoi_nhom6.Adapter.KhachHangAdapter;
import com.example.khuvuichoi_nhom6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.molder.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Fragment_QLKhachHang extends Fragment {
    ListView lv;
    private AppCompatActivity appCompatActivity;
    private ArrayList<KhachHang> list;
    FloatingActionButton fab;
    Dialog dialog;
    private KhachHang_Dao dao;
    private KhachHangAdapter adapter;
     KhachHang item;
    private EditText edMaKH, edHoTenKH, edDienThoaiKH, edDiaChiKH, edGmailKH;
    Button btnSave, btnCancel;
private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_khach_hang, container, false);
        lv = view.findViewById(R.id.lvKhachHang);
        fab = view.findViewById(R.id.fab);
        dao = new KhachHang_Dao(getActivity());
        capNhatLV();
//
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity());
            }
        });



        return view;
    }

    protected void openDialog(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.khachhang_dialog);
        edMaKH = dialog.findViewById(R.id.edMaKhachHang);
        edHoTenKH = dialog.findViewById(R.id.edHoTenKH);
        edDienThoaiKH = dialog.findViewById(R.id.edSoDTKH);
        edDiaChiKH = dialog.findViewById(R.id.edDiachiKH);
        edGmailKH = dialog.findViewById(R.id.edGmail);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);

//         Kiểm tra type insert 0 hay update 1
//        edMaKH.setEnabled(false);
//        if (type != 0) {
//            edMaKH.setText(item.getMaKH());
//            edHoTenKH.setText(item.getHoTen());
//            edDienThoaiKH.setText(item.getDienThoai());
//            edDiaChiKH.setText(item.getDiaChi());
//            edGmailKH.setText(item.getGmail());
//        }
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
                item = new KhachHang();
                item.setHoTen(edHoTenKH.getText().toString());
                item.setDienThoai(edDienThoaiKH.getText().toString());
                item.setDiaChi(edDiaChiKH.getText().toString());
                item.setGmail(edGmailKH.getText().toString());

                if (validate()>0){
                    item.setHoTen(edHoTenKH.getText().toString());
                    item.setDienThoai(edDienThoaiKH.getText().toString());
                    item.setDiaChi(edDiaChiKH.getText().toString());
                    item.setGmail(edGmailKH.getText().toString());
                        if (dao.addKH(item)>0){
                            Toast.makeText(context, "Thêm  thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm  không thành công", Toast.LENGTH_SHORT).show();

                        }
                  }
                capNhatLV();
                dialog.dismiss();
            }
        });
        dialog.show();


    }
    public void xoa(KhachHang khachHang){
        // Sử dụng AlertDialog
        dao = new KhachHang_Dao(getContext());
        item = new KhachHang();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dao.deleteKH(khachHang)>0){
                    Toast.makeText(getContext(), "Xóa loại sách thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                    dialogInterface.cancel();
                }else {
                    Toast.makeText(getContext(), "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();

                }
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
    public void capNhatLV() {
        dao = new KhachHang_Dao(getContext());
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);


    }


    public int validate(){
        int check = 1;
        if ( edHoTenKH.getText().length() == 0 ||
                edDienThoaiKH.getText().length() == 0 ||
                edDiaChiKH.getText().length() == 0 ||
                edGmailKH.getText().length() == 0){
            Toast.makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}