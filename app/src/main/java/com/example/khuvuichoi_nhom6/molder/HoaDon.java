package com.example.khuvuichoi_nhom6.molder;

public class HoaDon {
    private int id_hoadon;
    private int maKH;
    private int MaVe;
    private int giatien;
    private String time;
    private int thanhtoan;
    int statusHD;

    public HoaDon() {
    }

    public HoaDon(int id_hoadon, int maKH, int maVe, int giatien, String time, int thanhtoan, int statusHD) {
        this.id_hoadon = id_hoadon;
        this.maKH = maKH;
        MaVe = maVe;
        this.giatien = giatien;
        this.time = time;
        this.thanhtoan = thanhtoan;
        this.statusHD = statusHD;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int maVe) {
        MaVe = maVe;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public int getStatusHD() {
        return statusHD;
    }

    public void setStatusHD(int statusHD) {
        this.statusHD = statusHD;
    }
}