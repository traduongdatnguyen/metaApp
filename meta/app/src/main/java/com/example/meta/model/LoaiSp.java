package com.example.meta.model;

public class LoaiSp {
    int MaDM;
    String TenDM;
    String HinhAnh;
    String tamp = "http://192.168.100.24:8080/meta/public/assets/images/demos/demo-4/cats/";
    public int getId() {
        return MaDM;
    }

    public void setId(int MaDM) {
        this.MaDM = MaDM;
    }

    public String getTensanpham() {
        return TenDM;
    }

    public void setTensanpham(String TenDM) {
        this.TenDM = TenDM;
    }

    public String getHinhanh() {
        return HinhAnh;
    }

    public void setHinhanh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
}
