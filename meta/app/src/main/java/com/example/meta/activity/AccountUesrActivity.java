package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;

public class AccountUesrActivity extends AppCompatActivity {
    TextView usertendangnhap, userFullName,usersex,useraccphone,useraccemail,useraccaddress;
    FrameLayout framesearch,framegiohang,framemyusser;
    NotificationBadge badge;
    Toolbar toolbar;
    ImageView imageaccusser;
    LinearLayout editpass,edittenuser,edittentaikhoan,editgioitinh,editsodienthoai,editemail,edittaddressuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_uesr);
        initView();
        ActionToolBar();
        getData();
        btnEditPass();
        listOneditHoso();
    }


    private void btnEditPass(){
        editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditPassUserActivity.class );
                intent.putExtra("title", "Đổi mật khẩu");

                startActivity(intent);
            }
        });
    }


    private void getData() {
        Glide.with(getApplicationContext()).load(Utils.URL_IMGAEUSSER + Utils.user_current.getHinhAnh()).into(imageaccusser);
        userFullName.setText(Utils.user_current.getHo() + Utils.user_current.getTen());
        useraccaddress.setText(Utils.user_current.getDiaChi());
        usertendangnhap.setText(Utils.user_current.getTaiKhoan());
        useraccemail.setText(Utils.user_current.getEmail());
        useraccphone.setText(Utils.user_current.getSDT());
        usersex.setText(Utils.user_current.getGioiTinh());
    }


    private void initView() {
        imageaccusser = findViewById(R.id.imageaccusser);
        toolbar = findViewById(R.id.toobar);
        userFullName = findViewById(R.id.userFullName);
        usertendangnhap = findViewById(R.id.usertendangnhap);
        useraccaddress = findViewById(R.id.useraccaddress);
        usersex = findViewById(R.id.usersex);
        useraccphone = findViewById(R.id.usersex);
        useraccemail = findViewById(R.id.useraccemail);
        badge = findViewById(R.id.menu_sl);
        framegiohang = findViewById(R.id.framegiohang);
        framesearch = findViewById(R.id.framesearch);
        framemyusser = findViewById(R.id.framemyusser);
        editpass = findViewById(R.id.editpass);
        edittenuser = findViewById(R.id.edittenuser);
        edittentaikhoan = findViewById(R.id.edittentaikhoan);
        editgioitinh = findViewById(R.id.editgioitinh);
        editsodienthoai = findViewById(R.id.editsodienthoai);
        editemail = findViewById(R.id.editemail);
        edittaddressuser = findViewById(R.id.edittaddressuser);



        if(Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else{
            int tatolItem = 0;
            for (int i = 0; i < Utils.manggiohang.size() ; i++){
                tatolItem = tatolItem + Utils.manggiohang.get(i).getSoLuong();

            }
            badge.setText(String.valueOf(tatolItem));
        }
        framegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(giohang);
            }
        });
        framegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishlist = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(wishlist);
            }
        });
        framemyusser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myuser = new Intent(getApplicationContext(),PresonalActivity.class);
                startActivity(myuser);
            }
        });
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

    //nghe sự kiện chuyển - sửa hồ sơ

    private void listOneditHoso(){
        edittenuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditHoSoActivity.class );
                intent.putExtra("title", "Sửa tên");
                intent.putExtra("infoUser", Utils.user_current.getTen()+" "+Utils.user_current.getTen()+"");
                startActivity(intent);
            }
        });

        editgioitinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditHoSoActivity.class );
                intent.putExtra("title", "Sửa giới tính");
                intent.putExtra("infoUser", Utils.user_current.getGioiTinh()+"");
                startActivity(intent);
            }
        });
        editsodienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditHoSoActivity.class );
                intent.putExtra("title", "Điện thoại");
                intent.putExtra("infoUser", Utils.user_current.getSDT()+"");
                startActivity(intent);
            }
        });
        editemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditHoSoActivity.class );
                intent.putExtra("title", "Sửa email");
                intent.putExtra("infoUser", Utils.user_current.getEmail()+"");
                startActivity(intent);
            }
        });
        edittaddressuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditHoSoActivity.class );
                intent.putExtra("title", "Địa chỉ");
                intent.putExtra("infoUser", Utils.user_current.getDiaChi()+"");
                startActivity(intent);
            }
        });
    }
}