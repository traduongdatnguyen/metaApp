package com.example.meta.model;

import java.io.Serializable;

public class SanPhamMoi implements Serializable {
    int MaSP;
    int MaLSP;
    int MaDM;
    String TenSP;
    String DonGia;
    int SoLuong;
    String HinhAnh1, HinhAnh2, HinhAnh3, HinhAnh4, HinhAnh5, MaKM, ManHinh, HDH, CamSau, CamTruoc, CPU, Ram, Rom, SDCard, Pin, SoSao, SoDanhGia, TrangThai;
    String MoTa;
    String ThoiGian;
    String LoaiMau1, LoaiMau2, LoaiMau3, LoaiMau4, LoaiMau5;
//192.168.1.4   192.168.100.24
    String tamp="http://192.168.1.4:8080/meta/public/assets/images/products/";
    String HinhAnh,TenSanPham;


    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public int getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(int maLSP) {
        MaLSP = maLSP;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int maDM) {
        MaDM = maDM;
    }

    public String getTenSP() {
        TenSanPham = TenSP;
        return TenSanPham;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getDonGia() {

        return DonGia;
    }
    public void setDonGia(String donGia) {
        DonGia = donGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }
    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getHinhAnh1() {
        HinhAnh = tamp+HinhAnh1;
        return HinhAnh;
    }
    public void setHinhAnh1(String hinhAnh1) {
        HinhAnh1 = hinhAnh1;
    }

    public String getHinhAnh2() {
        return HinhAnh2;
    }

    public void setHinhAnh2(String hinhAnh2) {
        HinhAnh2 = hinhAnh2;
    }

    public String getHinhAnh3() {
        return HinhAnh3;
    }

    public void setHinhAnh3(String hinhAnh3) {
        HinhAnh3 = hinhAnh3;
    }

    public String getHinhAnh4() {
        return HinhAnh4;
    }

    public void setHinhAnh4(String hinhAnh4) {
        HinhAnh4 = hinhAnh4;
    }

    public String getHinhAnh5() {
        return HinhAnh5;
    }

    public void setHinhAnh5(String hinhAnh5) {
        HinhAnh5 = hinhAnh5;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }

    public String getManHinh() {
        return ManHinh;
    }

    public void setManHinh(String manHinh) {
        ManHinh = manHinh;
    }

    public String getHDH() {
        return HDH;
    }

    public void setHDH(String HDH) {
        this.HDH = HDH;
    }

    public String getCamSau() {
        return CamSau;
    }

    public void setCamSau(String camSau) {
        CamSau = camSau;
    }

    public String getCamTruoc() {
        return CamTruoc;
    }

    public void setCamTruoc(String camTruoc) {
        CamTruoc = camTruoc;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getRom() {
        return Rom;
    }

    public void setRom(String rom) {
        Rom = rom;
    }

    public String getSDCard() {
        return SDCard;
    }

    public void setSDCard(String SDCard) {
        this.SDCard = SDCard;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getSoSao() {
        return SoSao;
    }

    public void setSoSao(String soSao) {
        SoSao = soSao;
    }

    public String getSoDanhGia() {
        return SoDanhGia;
    }

    public void setSoDanhGia(String soDanhGia) {
        SoDanhGia = soDanhGia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
    public String getMota() {
        return MoTa;
    }

    public void setMota(String mota) {
        MoTa = mota;
    }
    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getLoaiMau1() {
        return LoaiMau1;
    }

    public void setLoaiMau1(String loaiMau1) {
        LoaiMau1 = loaiMau1;
    }

    public String getLoaiMau2() {
        return LoaiMau2;
    }

    public void setLoaiMau2(String loaiMau2) {
        LoaiMau2 = loaiMau2;
    }

    public String getLoaiMau3() {
        return LoaiMau3;
    }

    public void setLoaiMau3(String loaiMau3) {
        LoaiMau3 = loaiMau3;
    }

    public String getLoaiMau4() {
        return LoaiMau4;
    }

    public void setLoaiMau4(String loaiMau4) {
        LoaiMau4 = loaiMau4;
    }

    public String getLoaiMau5() {
        return LoaiMau5;
    }

    public void setLoaiMau5(String loaiMau5) {
        LoaiMau5 = loaiMau5;
    }




}


