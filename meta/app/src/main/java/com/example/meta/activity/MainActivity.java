package com.example.meta.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.adapter.DienThoaiMoiNhatAdapter;
import com.example.meta.adapter.LoaiSpAdapter;
import com.example.meta.adapter.SanPhamMoiAdapter;
import com.example.meta.model.LoaiSp;
import com.example.meta.model.SanPhamMoi;
import com.example.meta.model.SanPhamMoiModel;
import com.example.meta.retrofit.ApiBanHang;
import com.example.meta.retrofit.RetrofitClient;
import com.example.meta.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh,recycleview2;
    NavigationView navigationView;
    ListView listviewmanhinhchinh;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> listSPMoi;
    DienThoaiMoiNhatAdapter dienThoaiMNAdapterAdapter;
    SanPhamMoiAdapter spAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout,framewishlist,framemyuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        Anhxa();
        ActionBar();

        if (isConnected(this)) {
            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getdienthoai();
            getEventClick();
        } else {
            Toast.makeText(getApplicationContext(), "Kh??ng c?? internet", Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
           listviewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   switch (i) {
                        case 0:
                            Intent smartphone = new Intent(getApplicationContext(),DienThoaiActivity.class);
                            smartphone.putExtra("MaDM",1);
                            smartphone.putExtra("title", "Smartphone");
                            startActivity(smartphone);
                            break;

                        case 1:
                            Intent laptap = new Intent(getApplicationContext(),DienThoaiActivity.class);
                            laptap.putExtra("MaDM",2);
                            laptap.putExtra("title", "Laptop");
                            startActivity(laptap);
                            break;
                        case 2:
                            Intent tablet = new Intent(getApplicationContext(),DienThoaiActivity.class);
                            tablet.putExtra("MaDM",3);
                            tablet.putExtra("title", "Tablet");
                            startActivity(tablet);
                            break;
                        case 3:
                           Intent smartWatches = new Intent(getApplicationContext(),DienThoaiActivity.class);
                            smartWatches.putExtra("MaDM",4);
                            smartWatches.putExtra("title", "Smart Watches");
                           startActivity(smartWatches);
                           break;
                       case 4:
                           Intent DigtalCameras = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           DigtalCameras.putExtra("MaDM",5);
                           DigtalCameras.putExtra("title", "Digtal Cameras");
                           startActivity(DigtalCameras);
                           break;
                       case 5:
                           Intent Televisions = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           Televisions.putExtra("MaDM",6);
                           Televisions.putExtra("title", "Televisions");
                           startActivity(Televisions);
                           break;
                       case 6:
                           Intent Audio = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           Audio.putExtra("MaDM",7);
                           Audio.putExtra("title", "Audio");
                           startActivity(Audio);
                           break;
                       case 7:
                           Intent Accessoris = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           Accessoris.putExtra("MaDM",8);
                           Accessoris.putExtra("title", "Accessoris");
                           startActivity(Accessoris);
                           break;
                       case 8:
                           Intent PcPrinter = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           PcPrinter.putExtra("MaDM",9);
                           PcPrinter.putExtra("title", "Pc, Printer");
                           startActivity(PcPrinter);
                           break;
                       case 9:
                           Intent Waterheater = new Intent(getApplicationContext(),DienThoaiActivity.class);
                           Waterheater.putExtra("MaDM",10);
                           Waterheater.putExtra("title", "Water heater");
                           startActivity(Waterheater);
                           break;
                       case 10:
                           Intent wishList = new Intent(getApplicationContext(),WishlistActivity.class);
                           startActivity(wishList);
                           break;

                       default:
                            Intent trangchu2 = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(trangchu2);
                            break;
                   }
               }
           });

    }
//
    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                listSPMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(),listSPMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }
                        },throwable -> {
                            Toast.makeText(getApplicationContext(),"Kh??ng hi???n th??? ???????c s???n ph???m g???i ??!"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                )
        );
    }
    private void getdienthoai() {
        compositeDisposable.add(apiBanHang.getDienThoaiMn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                listSPMoi = sanPhamMoiModel.getResult();
                                dienThoaiMNAdapterAdapter = new DienThoaiMoiNhatAdapter(getApplicationContext(),listSPMoi);
                                recycleview2.setAdapter(dienThoaiMNAdapterAdapter);
                            }
                        },throwable -> {
                            Toast.makeText(getApplicationContext(),"Kh??ng hi???n th??? ???????c ??i???n tho???i m???i nh???t!"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if (loaiSpModel.isSuccess()) {
                                mangloaisp = loaiSpModel.getResult();
                                //khoitao adapter
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), mangloaisp);
                                listviewmanhinhchinh.setAdapter(loaiSpAdapter);
                            }
                        },
                        throwable -> {
                            Log.d("test", throwable.getMessage());
                        }
                ));

    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://192.168.100.24:8080/meta/public/assets/images/banners/promotionanner.png");
        mangquangcao.add("http://192.168.100.24:8080/meta/public/assets/images/banners/Apple-Banner.jpg");
        mangquangcao.add("http://192.168.100.24:8080/meta/public/assets/images/banners/Banner-Mac-5.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void Anhxa() {
        toolbar = findViewById(R.id.toobarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);

        recycleview2 = findViewById(R.id.recycleview2);
//        recycleview2.setLayoutManager(layoutManager);
//         recycleview2.setHasFixedSize(true);
//        //

        listviewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        framewishlist = findViewById(R.id.framewwishlist);
        framemyuser = findViewById(R.id.framemyusser);
        //khoi tao list
        mangloaisp = new ArrayList<>();
        listSPMoi = new ArrayList<>();
        if(Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else{
            int tatolItem = 0;
            for (int i = 0; i < Utils.manggiohang.size() ; i++){
                tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

            }
            badge.setText(String.valueOf(tatolItem));
        }
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
                Intent wishlist = new Intent(getApplicationContext(),SearchActivity.class);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        int tatolItem = 0;
        for (int i = 0; i < Utils.manggiohang.size() ; i++){
            tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

        }
        badge.setText(String.valueOf(tatolItem));
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}