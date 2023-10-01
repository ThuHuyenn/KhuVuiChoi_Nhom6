package com.example.khuvuichoi_nhom6.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khuvuichoi_nhom6.Database.DbHelper;
import com.example.khuvuichoi_nhom6.molder.KhachHang;
import com.example.khuvuichoi_nhom6.molder.Ve;

import java.util.ArrayList;
import java.util.List;

public class VeDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public VeDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    public void close() {
        dbHelper.close();
    }

    public long addVe(Ve obj) {
        ContentValues values = new ContentValues();

        values.put("status", obj.getStatus());
        return db.insert("ve", null, values);
    }

    public int updateVe(Ve obj) {
        ContentValues values = new ContentValues();

        values.put("status", obj.getStatus());
        return db.update("ve", values, "maVe=?", new String[]{String.valueOf(obj.getMaVe())});
    }

    public int deteleVe(String id) {
        return db.delete("ve", "maVe=?", new String[]{String.valueOf(id)});
    }

    public List<Ve> getAll() {
        String sql = "SELECT * FROM ve";
        return getData(sql);
    }

    @SuppressLint("Range")
    public List<Ve> getData(String sql, String... args) {
        List<Ve> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            Ve ve = new Ve();
          ve.setMaVe(cursor.getInt(cursor.getColumnIndex("maVe")));
          ve.setStatus(cursor.getInt(cursor.getColumnIndex("status")));

        }
        return list;

    }
}
