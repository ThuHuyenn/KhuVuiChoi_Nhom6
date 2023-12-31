package com.example.khuvuichoi_nhom6.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_nhom6.Database.DbHelper;
import com.example.khuvuichoi_nhom6.molder.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHang_Dao {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public KhachHang_Dao(Context context) {

        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void open() {
       db = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }

    public long addKH(KhachHang khachHang) {
        ContentValues values = new ContentValues();
//        values.put((KhachHang.TB_COL_ID),khachHang.getMaKH());
        values.put("hoTen", khachHang.getHoTen());
        values.put("dienThoai", khachHang.getDienThoai());
        values.put("diaChi", khachHang.getDiaChi());
        values.put("gmail",khachHang.getGmail());
        return db.insert("KhachHang",null,values);
    }
    public int updateKH(KhachHang khachHang) {
        ContentValues values = new ContentValues();
//        values.put((KhachHang.TB_COL_ID), khachHang.getMaKH());
        values.put("hoTen", khachHang.getHoTen());
        values.put("dienThoai", khachHang.getDienThoai());
        values.put("diaChi", khachHang.getDiaChi());
        values.put("gmail",khachHang.getGmail());
        return db.update("KhachHang", values, "maKH = ? ", new String[]{String.valueOf(khachHang.getMaKH())});
    }
    public int deleteKH(KhachHang khachHang) {
        return db.delete("KhachHang", "maKH = ? ", new String[]{String.valueOf(khachHang.getMaKH())});
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        List<KhachHang> list = getData(sql);
        return list;
    }
    public KhachHang getMaKH(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        List<KhachHang> list = getData(sql, maKH);
        return list.get(0);
    }

    public int checkMaKH(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        List<KhachHang> list = getData(sql, maKH);
        return list.size() == 0 ? -1 : 1;
    }
    @SuppressLint("Range")
    public int getCountKH() {
        String sql = "SELECT COUNT(*) AS SoLuong FROM KhachHang";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("SoLuong"));
    }
    @SuppressLint("Range")
    public int getCountVe() {
        String sql = "SELECT COUNT(*) AS SoLuong FROM ve";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("SoLuong"));
    }
    @SuppressLint("Range")
    public int getCountNV() {
        String sql = "SELECT COUNT(*) AS SoLuong FROM nhanvien";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("SoLuong"));
    }

    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String... args) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH((cursor.getInt(cursor.getColumnIndex(("maKH")))));
            khachHang.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            khachHang.setDienThoai(cursor.getString(cursor.getColumnIndex("dienThoai")));
            khachHang.setDiaChi(cursor.getString(cursor.getColumnIndex("diaChi")));
            khachHang.setGmail(cursor.getString(cursor.getColumnIndex("gmail")));
            list.add(khachHang);
        }
        return list;
    }



}
