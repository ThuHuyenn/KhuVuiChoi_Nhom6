package com.example.khuvuichoi_n6.Model;

public class HoaDon {
    private int id_hoadon;
    private int maKH;
    private int MaVe;
    private int giave;
    private String time;
    private String thanhtoan;


    public HoaDon() {
    }

    public HoaDon(int id_hoadon, int maKH, int maVe, int giave, String time, String thanhtoan) {
        this.id_hoadon = id_hoadon;
        this.maKH = maKH;
        MaVe = maVe;
        this.giave = giave;
        this.time = time;
        this.thanhtoan = thanhtoan;
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

    public int getGiave() {
        return giave;
    }

    public void setGiave(int giave) {
        this.giave = giave;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}
