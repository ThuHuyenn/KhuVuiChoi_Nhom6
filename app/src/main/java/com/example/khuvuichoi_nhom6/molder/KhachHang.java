package com.example.khuvuichoi_nhom6.molder;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String dienThoai;
    private String diaChi;
    private String gmail;




    public KhachHang(int maKH, String hoTen, String dienThoai, String diaChi, String gmail) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.gmail = gmail;
    }

    public KhachHang() {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.gmail = gmail;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }


}
