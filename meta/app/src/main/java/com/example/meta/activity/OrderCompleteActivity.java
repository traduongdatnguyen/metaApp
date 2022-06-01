package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meta.R;
import com.example.meta.adapter.DienThoaiAdapter;
import com.example.meta.adapter.MyPurchaseAdapter;
import com.example.meta.model.MyPurchaseModel;
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

public class OrderCompleteActivity extends AppCompatActivity {
    Button btndeletedonhang;
    TextView thongbaotrangthai;
    FrameLayout frameLayout,framewishlist,framemyuser;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    NotificationBadge badge;
    List<SanPhamMoi> donhangList;
    MyPurchaseAdapter myPurchaseAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    int MaND = Utils.user_current.getMaND();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        initView();
        ActionToolBar();
        initData();

    }

    private void initData() {

        compositeDisposable.add(apiBanHang.myPurchase(MaND)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myPurchaseModel -> {
                            if(myPurchaseModel.isSuccess()){

                                donhangList = myPurchaseModel.getResult();
                                myPurchaseAdapter = new MyPurchaseAdapter(getApplicationContext(),donhangList);
                                recyclerView.setAdapter(myPurchaseAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối sever",Toast.LENGTH_LONG).show();
                        }
                ));

        btndeletedonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedonHang();
                Intent intent = new Intent(getApplicationContext(), OrderCompleteActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void deletedonHang(){
        compositeDisposable.add(apiBanHang.deleteMypurchase(MaND)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myPurchaseModel -> {
                            if(myPurchaseModel.isSuccess()){
                                Toast.makeText(getApplicationContext(),"Hủy đơn hàng thành công.",Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Hủy đơn hàng thành công",Toast.LENGTH_LONG).show();
                        }
                ));
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
    private void initView() {
        toolbar = findViewById(R.id.toobar);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        recyclerView = findViewById(R.id.listdonhangdadat);
        btndeletedonhang = findViewById(R.id.btndeletedonhang);
        thongbaotrangthai = findViewById(R.id.thongbaotrangthai);
        frameLayout = findViewById(R.id.framegiohang);
        framewishlist = findViewById(R.id.framewwishlist);
        framemyuser = findViewById(R.id.framemyusser);
        badge = findViewById(R.id.menu_sl);
        donhangList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}