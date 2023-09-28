package com.example.khuvuichoi_nhom6.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_nhom6.Database.DbHelper;

public class QuanLy_Dao {
    private DbHelper dbHelper;

    public QuanLy_Dao(Context context){

        dbHelper = new DbHelper(context);
    }
    //login
    public boolean checkLogin(String username , String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM quanly WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        return  cursor.getCount() > 0;
    }

    public long Rgister(String username, String pas, String hoten, Integer sdt) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", pas);
        contentValues.put("hoten", hoten);
        contentValues.put("hoten", sdt);

        long result = sqLiteDatabase.insert("quanly", null, contentValues);
        return result;
    }
}


