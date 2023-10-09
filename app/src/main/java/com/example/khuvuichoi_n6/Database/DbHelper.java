package com.example.khuvuichoi_n6.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"QLDA_Nhom6_1",null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QuanLy = "CREATE TABLE quanly(tendangnhap TEXT PRIMARY KEY, matkhau TEXT, hoten TEXT,sdt INTEGER)";
        db.execSQL(QuanLy);
        String sqlQuanLy    = "INSERT INTO quanly VALUES('quanly','123','Nguyen Thị Thu Huyền',011111)";
        db.execSQL(sqlQuanLy);
        String NhanVien = "CREATE TABLE nhanvien(id INTEGER PRIMARY KEY , tendangnhap TEXT ,matkhau TEXT, sdt INTEGER, diachi TEXT, email TEXT,status INTEGER NOT NULL)";
        db.execSQL(NhanVien);
        String sqlNhanVien    = "INSERT INTO nhanvien VALUES(1,'nhanvien','123',0111,'nhà A','huyen@gmail.com',1)";
        db.execSQL(sqlNhanVien);
        // khach hang
        String KhachHang = "CREATE TABLE KhachHang(" +
                "maKH INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL ," +
                "dienThoai TEXT NOT NULL," +
                "diaChi TEXT NOT NULL," +
                "gmail TEXT NOT NULL)";
        db.execSQL(KhachHang);
        String ve = "CREATE TABLE ve (" +
                "maVe INTEGER PRIMARY KEY AUTOINCREMENT," +
                "status TEXT NOT NULL )";
        db.execSQL(ve);
        String hoadon = "CREATE TABLE hoadon(id_hoadon INTEGER PRIMARY KEY AUTOINCREMENT, maKH INTEGER REFERENCES KhachHang(maKH) , MaVe INTEGER REFERENCES ve(MaVe) ,giave INTEGER, time TEXT,thanhtoan TEXT )";
        db.execSQL(hoadon);
        String sqlhoadon    = "INSERT INTO hoadon VALUES(1,1,1,0111,'31/08/2003','Tiềm mặt')";
        db.execSQL(sqlhoadon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS quanly");
            db.execSQL("DROP TABLE IF EXISTS nhanvien");
            db.execSQL("DROP TABLE IF EXISTS  KhachHang");
            db.execSQL("DROP TABLE IF EXISTS  ve");
            db.execSQL("DROP TABLE IF EXISTS  hoadon");
            onCreate(db);
        }
    }
}