package com.example.khuvuichoi_n6.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_n6.Database.DbHelper;
import com.example.khuvuichoi_n6.Model.HoaDon;
import com.example.khuvuichoi_n6.Model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class HoaDon_Dao {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public HoaDon_Dao(Context context) {

        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //lấy danh sách SQL
    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM hoadon";
        List<HoaDon> list = getData(sql);
        return list;
    }
    public void close() {
        dbHelper.close();
    }
    public List<HoaDon> getData(String sql, String... args) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId_hoadon((cursor.getInt(cursor.getColumnIndex(("id_hoadon")))));
            hoaDon.setMaKH(cursor.getInt(cursor.getColumnIndex("maKH")));
            hoaDon.setMaVe(cursor.getInt(cursor.getColumnIndex("MaVe")));
            hoaDon.setGiave(cursor.getInt(cursor.getColumnIndex("giave")));
            hoaDon.setTime(cursor.getString(cursor.getColumnIndex("time")));
            hoaDon.setThanhtoan(cursor.getString(cursor.getColumnIndex("thanhtoan")));
            list.add(hoaDon);
        }
        return list;
    }
}
