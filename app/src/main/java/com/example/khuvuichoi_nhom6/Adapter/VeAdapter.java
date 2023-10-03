package com.example.khuvuichoi_nhom6.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khuvuichoi_nhom6.Dao.KhachHang_Dao;
import com.example.khuvuichoi_nhom6.Dao.VeDao;
import com.example.khuvuichoi_nhom6.R;
import com.example.khuvuichoi_nhom6.fragment.Fragment_DangKyVe;
import com.example.khuvuichoi_nhom6.molder.KhachHang;
import com.example.khuvuichoi_nhom6.molder.Ve;

import java.util.ArrayList;

public class VeAdapter  extends ArrayAdapter<Ve> {
    private Context context;

Fragment_DangKyVe fragmentDangKyVe;
    private ArrayList<Ve> list;
    TextView tvMaVe,tvStatus;
    ImageView imgDel,imgUpdate;
    private Dialog dialog;
    EditText edMaVe,edVe;
    Button btnSave,btnCancel;
    private VeDao dao;

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

             tvStatus.setText("Ve: "+item.getStatus());

            imgDel = v.findViewById(R.id.imgDeleteLS);
            imgUpdate= v.findViewById(R.id.imgUpdateVe);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentDangKyVe.xoa(item);
            }
        });
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update(context,item);
            }
        });
        return v;

    }
   protected void Update(Context context, Ve item){
       dialog = new Dialog(context);
       dialog.setContentView(R.layout.ve_dialog);
       edMaVe = dialog.findViewById(R.id.edMaVe);
       edVe = dialog.findViewById(R.id.edVe);
       btnCancel = dialog.findViewById(R.id.btnCancelVe);
       btnSave = dialog.findViewById(R.id.btnSaveVe);

       edVe.setText(item.getStatus());
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
            item.setStatus(edVe.getText().toString());
               if (dao.updateVe(item)>0){
//
                   Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                   capNhatLV();

               }else {
                   Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();

               }
//                    capNhatLV();
               dialog.dismiss();
           }
       });
       dialog.show();

   }
    public void capNhatLV() {
        dao = new VeDao(getContext());
        list = (ArrayList<Ve>) dao.getAll();
        notifyDataSetChanged();

    }

}
