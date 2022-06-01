package com.example.meta.retrofit;


import com.example.meta.model.DienThoaiMoiModel;
import com.example.meta.model.LoaiSpModel;
import com.example.meta.model.MyPurchaseModel;
import com.example.meta.model.SanPhamMoiModel;
import com.example.meta.model.UserModel;
import com.example.meta.model.WishlistModel;
import com.example.meta.utils.Utils;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiBanHang {

    String loaisanpham = "getloaisp.php";
    String getSpmoi = "getspmoi.php";
    String getSanPhamCt = "chitiet.php";

    @GET(loaisanpham)
    Observable<LoaiSpModel> getLoaiSp();


    @GET(getSpmoi)
    Observable<SanPhamMoiModel> getSpMoi();

    @GET("dienthoaimoinhat.php")
    Observable<DienThoaiMoiModel> getDienThoaiMn();

    @POST(getSanPhamCt)
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
            @Field("MaDM") int MaDM
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("Ten") String Ten,
            @Field("Email") String Email,
            @Field("TaiKhoan") String TaiKhoan,
            @Field("MatKhau") String MatKhau
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("TaiKhoan") String TaiKhoan,
            @Field("MatKhau") String MatKhau
    );

    @POST("thanhtoan.php")
    @FormUrlEncoded
    Observable<UserModel> checkout(
            @Field("MaND") int MaND,
            @Field("NguoiNhan") String NguoiNhan,
            @Field("SDT") String SDT,
            @Field("DiaChi") String DiaChi,
            @Field("Note") String Note,
            @Field("TongTien") int TongTien,
            @Field("chitietdonhang") String chitietdonhang
    );

    @POST("themyeuthich.php")
    @FormUrlEncoded
    Observable<WishlistModel> addwishlist(
            @Field("MaND") int MaND,
            @Field("MaSP") int MaSP,
            @Field("TenSP") String TenSP,
            @Field("HinhAnh") String HinhAnh,
            @Field("DonGia") int DonGia
    );

    @POST("viewyeuthich.php")
    @FormUrlEncoded
    Observable<WishlistModel> wishlist(
            @Field("MaND") int MaND
    );

    @POST("danhsmypurchase.php")
    @FormUrlEncoded
    Observable<MyPurchaseModel> myPurchase(
            @Field("MaND") int MaND
    );

    @POST("deletemypurchase.php")
    @FormUrlEncoded
    Observable<MyPurchaseModel> deleteMypurchase(
            @Field("MaND") int MaND
    );

    @POST("search.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );
}

