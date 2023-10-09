package com.example.khuvuichoi_n6.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khuvuichoi_n6.Adapter.NhanVien_Adapter;
import com.example.khuvuichoi_n6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_n6.Dao.NhanVien_Dao;
import com.example.khuvuichoi_n6.Model.KhachHang;
import com.example.khuvuichoi_n6.Model.NhanVien;
import com.example.khuvuichoi_n6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_QuanLyNhaVien extends Fragment {

    private NhanVien_Dao nhanVien_dao;
    private ArrayList<NhanVien> list;
    private NhanVien_Adapter adapter;
    FloatingActionButton fab;
    Dialog dialog;
    NhanVien item;
    private EditText ed_id, ed_ten, ed_pass, ed_sdt, ed_diachi,ed_email;
    Button btnSave, btnCancel;
    ListView lv;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_nha_vien_, container, false);
        lv = view.findViewById(R.id.lvKhachHang);
        fab = view.findViewById(R.id.add);

        nhanVien_dao = new NhanVien_Dao(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity());
            }
        });

        return view;
    }
    public void capNhatLV() {
        nhanVien_dao = new NhanVien_Dao(getContext());
        list = (ArrayList<NhanVien>) nhanVien_dao.getAll();
        adapter = new NhanVien_Adapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }
    protected void openDialog(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_nhanvien);
        ed_id = dialog.findViewById(R.id.ed_id);
        ed_ten = dialog.findViewById(R.id.ed_tendn);
        ed_pass = dialog.findViewById(R.id.ed_matkhau);
        ed_sdt = dialog.findViewById(R.id.ed_sdt);
        ed_diachi = dialog.findViewById(R.id.ed_diachi);
        ed_email = dialog.findViewById(R.id.ed_email);
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
                nhanVien_dao = new NhanVien_Dao(getContext());
                item = new NhanVien();

                item.setTendangnhap(ed_ten.getText().toString());
                item.setMatkhau(ed_pass.getText().toString());
                item.setSdt(Integer.parseInt(ed_sdt.getText().toString()));
                item.setDiachi(ed_diachi.getText().toString());
                item.setEmail(ed_email.getText().toString());

                if (valiDate()){

                    if (nhanVien_dao.addKH(item)>0){
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
    public boolean valiDate() {
        if (
                ed_ten.getText().length() == 0 ||
                        ed_pass.getText().length() == 0 ||
                        ed_sdt.getText().length() == 0 ||
                        ed_diachi.getText().length() == 0 ||
                        ed_email.getText().length() == 0) {


            ed_ten.setError("Vui lòng không để trống");
            ed_pass.setError("Vui lòng không để trống");
            ed_sdt.setError("Vui lòng không để trống");
            ed_diachi.setError("Vui lòng không để trống");
            ed_email.setError("Vui lòng không để trống");
            return false;
        }

//        if (edDienThoaiKH.getText().length() < 10 ||
//                edDienThoaiKH.getText().length() > 11 ||
//                Pattern.matches("[a-zA-Z]+", edDienThoaiKH.getText().toString())) {
//            Toast.makeText(appCompatActivity, "Sai độ dài số điện thoại", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }
    public void xoa(NhanVien nhanVien){
        // Sử dụng AlertDialog
        nhanVien_dao = new NhanVien_Dao(getContext());
        item = new NhanVien();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nhanVien_dao.deleteKH(nhanVien)>0){
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
}