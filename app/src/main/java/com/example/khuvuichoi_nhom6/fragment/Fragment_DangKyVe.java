package com.example.khuvuichoi_nhom6.fragment;

import static com.example.khuvuichoi_nhom6.R.id.lvVe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.khuvuichoi_nhom6.Adapter.VeAdapter;
import com.example.khuvuichoi_nhom6.Dao.VeDao;
import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.molder.Ve;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_DangKyVe extends Fragment {
    ListView lv;
    ArrayList<Ve> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaVe;
    CheckBox chkStatus;
    Button btnSave,btnCancel;
 Ve item;
    static VeDao dao;
   VeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment__dang_ky_ve, container, false);
        lv = view.findViewById(lvVe);
        fab = view.findViewById(R.id.fab);
        dao = new VeDao(getActivity());
       capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;// 0.Add
            }
        });

        return view;
    }
//    protected void openDialog(final Context context, final int type){
//        // custom dialog
//        dialog = new Dialog(context);
//        dialog.setContentView(R.layout.ve_dialog);
//        edMaVe= dialog.findViewById(R.id.edMaVe);
//        chkStatus = dialog.findViewById(R.id.chkStatus);
//        btnCancel = dialog.findViewById(R.id.btnCancelVe);
//        btnSave = dialog.findViewById(R.id.btnSaveVe);
//        // Kiểm tra type insert 0 hay update 1
////        edMaVe.setEnabled(false);
////        if (type != 0){
////            edMaVe.setText((item.getMaVe()));
////            if (item.getStatus()==1){
////                chkStatus.setChecked(true);
////            }else {
////                chkStatus.setChecked(false);
////            }
////        }
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dao = new VeDao(getContext());
//                item = new Ve();
//                if (chkStatus.isChecked()){
//                    item.getStatus();
//                }else {
//                    item.getStatus();
//                }
//
//
//                        // type = 0 (insert)
//                        if (dao.addVe(item)>0){
//                            Toast.makeText(context, "Thêm ve thành công", Toast.LENGTH_SHORT).show();
//                            capNhatLV();
//                        }else {
//                            Toast.makeText(context, "Thêm ve không thành công", Toast.LENGTH_SHORT).show();
//
//                        }
//               }
//
//
//
//        });
//        dialog.show();
//    }
    void capNhatLV(){
        dao = new VeDao(getContext());
        list = (ArrayList<Ve>) dao.getAll();
        adapter = new VeAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

}