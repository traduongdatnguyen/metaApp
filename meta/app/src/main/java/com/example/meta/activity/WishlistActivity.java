package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.meta.R;
import com.example.meta.adapter.WishlistAdapter;
import com.example.meta.model.Wishlist;
import com.example.meta.retrofit.ApiBanHang;
import com.example.meta.retrofit.RetrofitClient;
import com.example.meta.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class WishlistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<Wishlist> wishlists;
    int MaND = Utils.user_current.getMaND();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        initView();
        AcctionToolBar();
        getWishlist();
    }

        private void AcctionToolBar() {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    private void getWishlist() {

        compositeDisposable.add(apiBanHang.wishlist(MaND)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        WishlistModel -> {
                            if (WishlistModel.isSuccess()){
                                wishlists = WishlistModel.getResult();
                                wishlistAdapter = new WishlistAdapter(getApplicationContext(),wishlists);
                                recyclerView.setAdapter(wishlistAdapter);
                            }
                        },throwable -> {
                            Toast.makeText(getApplicationContext(),"Không hiển thị được sản phẩm yêu thích!"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void initView() {
        wishlists = new ArrayList<>();
        toolbar = findViewById(R.id.toobar);

        recyclerView = findViewById(R.id.recycleviewwishlist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }
}