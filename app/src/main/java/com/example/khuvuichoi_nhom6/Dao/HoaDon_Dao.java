package com.example.khuvuichoi_nhom6.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_nhom6.Database.DbHelper;
import com.example.khuvuichoi_nhom6.molder.HoaDon;

import java.util.ArrayList;

public class HoaDon_Dao {
    SQLiteDatabase db;

    private DbHelper dbHelper;
    public HoaDon_Dao(Context context){
        dbHelper = new DbHelper(context);
    }
    //lấy danh sách SQL
    public ArrayList<HoaDon> getDs(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM hoadon",null);//sửa table
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            do{
                list.add(new HoaDon(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //xóa
    public  boolean delete(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("hoadon", "id_hoadon = ?",new String[]{String.valueOf(id)});//sửa table
        if(check<=0) return  false;
        return true;
    }

    //update
    public boolean update(HoaDon hoadon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_hoadon",hoadon.getId_hoadon());
        contentValues.put("maKH",hoadon.getMaKH());
        contentValues.put("MaVe",hoadon.getMaVe());
        contentValues.put("giatien",hoadon.getGiatien());
        contentValues.put("time",hoadon.getTime());
        contentValues.put("thanhtoan",hoadon.getThanhtoan());
        contentValues.put("statusHD",hoadon.getStatusHD());

        int check = sqLiteDatabase.update("hoadon",contentValues,"id_hoadon = ?",new String[]{String.valueOf(hoadon.getId_hoadon())});
        if(check <= 0) return false;
        return true;

    }
    public int DoanhThu(String tuNgay, String denNgay){
        db = dbHelper.getWritableDatabase();
        int dt = 0;
        String sqlDT = "SELECT SUM(tienTT) as doanhThu FROM hoadon WHERE time BETWEEN ? AND ?";
        Cursor cs = db.rawQuery(sqlDT, new String[]{tuNgay, denNgay});
        if (cs.getCount() != 0) {
            cs.moveToFirst();
            dt = cs.getInt(0);
        }
        return dt;
    }

}


