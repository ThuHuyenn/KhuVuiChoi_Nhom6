package com.example.khuvuichoi_n6.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_n6.Database.DbHelper;

import com.example.khuvuichoi_n6.Model.KhachHang;
import com.example.khuvuichoi_n6.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVien_Dao {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public NhanVien_Dao(Context context){

        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM nhanvien";
        List<NhanVien> list = getData(sql);
        return list;
    }
    public void close() {
        dbHelper.close();
    }
    //login
    public boolean checkLogin_nhanvien(String username , String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nhanvien WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        return  cursor.getCount() > 0;
    }
    public List<NhanVien> getData(String sql, String... args) {
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId((cursor.getInt(cursor.getColumnIndex(("id")))));
            nhanVien.setTendangnhap(cursor.getString(cursor.getColumnIndex("tendangnhap")));
            nhanVien.setMatkhau(String.valueOf(cursor.getInt(cursor.getColumnIndex("matkhau"))));
            nhanVien.setSdt(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sdt"))));
            nhanVien.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
            nhanVien.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            nhanVien.setStatus(Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))));

            list.add(nhanVien);
        }
        return list;
    }
    public long addKH(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
//        values.put((KhachHang.TB_COL_ID),khachHang.getMaKH());
        values.put("tendangnhap", nhanVien.getTendangnhap());
        values.put("matkhau", nhanVien.getMatkhau());
        values.put("sdt", nhanVien.getSdt());
        values.put("diachi",nhanVien.getDiachi());
        values.put("email",nhanVien.getEmail());
        values.put("status", nhanVien.getStatus());


        return db.insert("nhanvien",null,values);
    }
    public int deleteKH(NhanVien nhanVien) {
        return db.delete("nhanvien", "id = ? ", new String[]{String.valueOf(nhanVien.getId())});
    }
    public int updateNv(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
//        values.put((KhachHang.TB_COL_ID), khachHang.getMaKH());
        values.put("id", nhanVien.getId());
        values.put("tendangnhap", nhanVien.getTendangnhap());
        values.put("matkhau", nhanVien.getMatkhau());
        values.put("sdt",nhanVien.getSdt());
        values.put("diachi", nhanVien.getDiachi());
        values.put("email",nhanVien.getEmail());
        values.put("status",nhanVien.getStatus());
        return db.update("nhanVien", values, "id = ? ", new String[]{String.valueOf(nhanVien.getId())});
    }
    public NhanVien getMaKH(String id) {
        String sql = "SELECT * FROM nhanvien WHERE id = ?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }
    public int checkMaKH(String id) {
        String sql = "SELECT * FROM nhanvien WHERE maKH = ?";
        List<NhanVien> list = getData(sql, id);
        return list.size() == 0 ? -1 : 1;
    }
}


