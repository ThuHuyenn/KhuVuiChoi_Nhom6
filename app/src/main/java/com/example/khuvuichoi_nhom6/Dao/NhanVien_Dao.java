package com.example.khuvuichoi_nhom6.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_nhom6.Database.DbHelper;
public class NhanVien_Dao {
    private DbHelper dbHelper;
    public NhanVien_Dao(Context context){
        dbHelper = new DbHelper(context);
    }


    //login
    public boolean checkLogin_nhanvien(String username , String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nhanvien WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        return  cursor.getCount() > 0;
    }

}


