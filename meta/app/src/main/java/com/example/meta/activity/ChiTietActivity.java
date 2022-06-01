package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.adapter.DienThoaiAdapter;
import com.example.meta.adapter.DienThoaiMoiNhatAdapter;
import com.example.meta.model.GioHang;
import com.example.meta.model.LoaiSp;
import com.example.meta.model.SanPhamMoi;
import com.example.meta.retrofit.ApiBanHang;
import com.example.meta.retrofit.RetrofitClient;
import com.example.meta.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChiTietActivity extends AppCompatActivity {
    TextView TenSP, DonGia, MoTa;
    Button btnthem, btnlove;
    ImageView HinhAnh, HinhAnh1,HinhAnh2;
    Spinner spinner;
    Toolbar toolbar;
    SanPhamMoi sanPhamMoi;
    NotificationBadge badge;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    FrameLayout frameLayout,framewishlist,framemyuser;
    int id;
    List<SanPhamMoi> sanPhamMoiList;
    DienThoaiMoiNhatAdapter dttuongtu;
    RecyclerView rvlisttuongtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themGioHang();

            }
        });
        btnlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themwishlist();
                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void themwishlist(){
        int str_MaND = Utils.user_current.getMaND();
        sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        int str_MaSP = sanPhamMoi.getMaSP();
        String str_TenSP = sanPhamMoi.getTenSP();
        String str_HinhAnh = sanPhamMoi.getHinhAnh1();
        int str_DonGia = Integer.parseInt(sanPhamMoi.getDonGia());


        compositeDisposable.add(apiBanHang.addwishlist(str_MaND,str_MaSP,str_TenSP,str_HinhAnh,str_DonGia)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        wishlistModel -> {
                            if(wishlistModel.isSuccess()){

                            }else{
                                Toast.makeText(getApplicationContext(),wishlistModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                )
        );

    }
    private void themGioHang() {
        if (Utils.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0 ; i < Utils.manggiohang.size(); i++) {
                if (Utils.manggiohang.get(i).getMaSP() == sanPhamMoi.getMaSP()) {
                    Utils.manggiohang.get(i).setSoLuong(soluong + Utils.manggiohang.get(i).getSoLuong());
                    long gia = Long.parseLong(sanPhamMoi.getDonGia()) * Utils.manggiohang.get(i).getSoLuong();

                    flag =true;
                }
            }
            if (flag == false){
                long gia = Long.parseLong(sanPhamMoi.getDonGia()) ;
                GioHang gioHang = new GioHang();
                gioHang.setDonGia(gia);
                gioHang.setSoLuong(soluong);
                gioHang.setMaSP(sanPhamMoi.getMaSP());
                gioHang.setTenSP(sanPhamMoi.getTenSP());
                gioHang.setHinhAnh(sanPhamMoi.getHinhAnh1());
                Utils.manggiohang.add(gioHang);
            }
        }else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(sanPhamMoi.getDonGia());
            GioHang gioHang = new GioHang();
            gioHang.setDonGia(gia);
            gioHang.setSoLuong(soluong);
            gioHang.setMaSP(sanPhamMoi.getMaSP());
            gioHang.setTenSP(sanPhamMoi.getTenSP());
            gioHang.setHinhAnh(sanPhamMoi.getHinhAnh1());
            Utils.manggiohang.add(gioHang);
        }
        int tatolItem = 0;
        for (int i = 0; i < Utils.manggiohang.size() ; i++){
            tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

        }
        badge.setText(String.valueOf(tatolItem));

    }

    private void initData() {
        sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        id = sanPhamMoi.getMaDM();
        TenSP.setText(sanPhamMoi.getTenSP());
        DonGia.setText("$ "+sanPhamMoi.getDonGia());
        MoTa.setText(
            "Operating system: "+ sanPhamMoi.getHDH() +"\n" +
             "Ram: "+ sanPhamMoi.getRam() +"\n"
             + "Front camera: " + sanPhamMoi.getCamTruoc() +"\n"
            + "Rear camera: " + sanPhamMoi.getCamSau() +"\n"
            + "Pin: " + sanPhamMoi.getPin() +"\n"
        );
        Glide.with(getApplicationContext()).load(Utils.URL_IMAGE + sanPhamMoi.getHinhAnh1()).into(HinhAnh);
        Glide.with(getApplicationContext()).load(Utils.URL_IMAGE + sanPhamMoi.getHinhAnh2()).into(HinhAnh1);
        Glide.with(getApplicationContext()).load(Utils.URL_IMAGE + sanPhamMoi.getHinhAnh3()).into(HinhAnh2);

        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adaptersprin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adaptersprin);

        compositeDisposable.add(apiBanHang.getSanPham(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                sanPhamMoiList = sanPhamMoiModel.getResult();
                                dttuongtu = new DienThoaiMoiNhatAdapter(getApplicationContext(),sanPhamMoiList);
                                rvlisttuongtu.setAdapter(dttuongtu);
                            }
                        },throwable -> {
                            Toast.makeText(getApplicationContext(),"Không hiển thị được điện thoại mới nhất!"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                )
        );


    }

    private void initView() {
        sanPhamMoiList = new ArrayList<>();
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        TenSP = findViewById(R.id.txttensp);
        DonGia = findViewById(R.id.txtgiasp);
        MoTa = findViewById(R.id.txtmotachitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        btnlove = findViewById(R.id.btnlove);
        spinner = findViewById(R.id.spinner);
        HinhAnh = findViewById(R.id.imgchitiet);
        HinhAnh1 = findViewById(R.id.cthinhanh3);
        HinhAnh2 = findViewById(R.id.cthinhanh2);
        toolbar = findViewById(R.id.toobar);



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvlisttuongtu = findViewById(R.id.listtuongduong);
        rvlisttuongtu.setLayoutManager(layoutManager);
        rvlisttuongtu.setHasFixedSize(true);

        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        framewishlist = findViewById(R.id.framewwishlist);
        framemyuser = findViewById(R.id.framemyusser);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(giohang);
            }
        });
        framewishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishlist = new Intent(getApplicationContext(),WishlistActivity.class);
                startActivity(wishlist);
            }
        });
        framemyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myuser = new Intent(getApplicationContext(),PresonalActivity.class);
                startActivity(myuser);
            }
        });

        if (Utils.manggiohang != null){
            int tatolItem = 0;
            for (int i = 0; i < Utils.manggiohang.size() ; i++){
                tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

            }

            badge.setText(String.valueOf(tatolItem));
        }

    }
    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.manggiohang != null){
            int tatolItem = 0;
            for (int i = 0; i < Utils.manggiohang.size() ; i++){
                tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

            }

            badge.setText(String.valueOf(tatolItem));
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}