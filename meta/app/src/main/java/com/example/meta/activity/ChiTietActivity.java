package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.model.GioHang;
import com.example.meta.model.SanPhamMoi;
import com.example.meta.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

public class ChiTietActivity extends AppCompatActivity {
    TextView TenSP, DonGia, MoTa;
    Button btnthem;
    ImageView HinhAnh;
    Spinner spinner;
    Toolbar toolbar;
    SanPhamMoi sanPhamMoi;
    NotificationBadge badge;

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
        TenSP.setText(sanPhamMoi.getTenSP());
        DonGia.setText("$ "+sanPhamMoi.getDonGia());
        MoTa.setText(
            "Operating system: "+ sanPhamMoi.getHDH() +"\n" +
             "Ram: "+ sanPhamMoi.getRam() +"\n"
             + "Front camera: " + sanPhamMoi.getCamTruoc() +"\n"
            + "Rear camera: " + sanPhamMoi.getCamSau() +"\n"
            + "Pin: " + sanPhamMoi.getPin() +"\n"
        );
        Glide.with(getApplicationContext()).load(sanPhamMoi.getHinhAnh1()).into(HinhAnh);
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adaptersprin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adaptersprin);

    }

    private void initView() {
        TenSP = findViewById(R.id.txttensp);
        DonGia = findViewById(R.id.txtgiasp);
        MoTa = findViewById(R.id.txtmotachitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        HinhAnh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toobar);
        badge = findViewById(R.id.menu_sl);
        FrameLayout frameLayoutgiohang = findViewById(R.id.framegiohang);

        frameLayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
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
}