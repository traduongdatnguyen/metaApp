package com.example.meta.retrofit;


import com.example.meta.model.LoaiSpModel;
import com.example.meta.model.SanPhamMoiModel;
import com.example.meta.model.UserModel;

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


}

