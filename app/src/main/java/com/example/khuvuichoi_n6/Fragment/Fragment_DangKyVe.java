package com.example.khuvuichoi_n6.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khuvuichoi_n6.Adapter.VeAdapter;
import com.example.khuvuichoi_n6.Dao.VeDao;
import com.example.khuvuichoi_n6.Model.Ve;
import com.example.khuvuichoi_n6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_DangKyVe extends Fragment {


    ListView lv;
    ArrayList<Ve> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaVe,edVe;
    CheckBox chkStatus;
    Button btnSave,btnCancel;
    Ve item;
    static VeDao dao;
    private VeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dang_ky_ve_, container, false);
        lv = view.findViewById(R.id.lvVe);
        fab = view.findViewById(R.id.fab);
        dao = new VeDao(getActivity());
        capNhatLV();
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDialog(getActivity(),1);
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity());
            }
        });


        return view;
    }
    protected void openDialog(final Context context){
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.ve_dialog);
        edMaVe= dialog.findViewById(R.id.edMaVe);
        edVe = dialog.findViewById(R.id.edVe);
        btnCancel = dialog.findViewById(R.id.btnCancelVe);
        btnSave = dialog.findViewById(R.id.btnSaveVe);
        // Kiểm tra type insert 0 hay update 1
//        edMaVe.setEnabled(false);
//        if (type != 0){
//            edMaVe.setText(item.getMaVe());
//            edVe.setText(item.getStatus());
//
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
                dao = new VeDao(getContext());
                item = new Ve();
                item.setStatus(edVe.getText().toString());

                if (validate()>0){

                    // type = 0 (insert)

                    if (dao.addVe(item)>0){
                        Toast.makeText(context, "Thêm  thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();

                    }

                }
                capNhatLV();
                dialog.dismiss();
            }

        });
        dialog.show();
    }
    public void xoa(Ve obj){
        // Sử dụng AlertDialog
        dao = new VeDao(getContext());
        item = new Ve();


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dao.deteleVe(obj)>0){
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
    void capNhatLV(){
        dao = new VeDao(getContext());
        list = (ArrayList<Ve>) dao.getAll();
        adapter = new VeAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edVe.getText().length()==0){
            Toast.makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }


}