package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.utils.Utils;

public class PresonalActivity extends AppCompatActivity {
    Button btnAccount, btnMyPurchase, btnEditAccount, btnShoppinCart, btnWhish;
    TextView userFullName, userGmail;
    ImageView imgUseracc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presonal);
        intiView();
        initControl();
        initData();
    }

    private void initData() {
        userFullName.setText(Utils.user_current.getHo()+" "+Utils.user_current.getTen()+"");
        userGmail.setText(Utils.user_current.getEmail()+"");
        Glide.with(getApplicationContext()).load(Utils.URL_IMGAEUSSER + Utils.user_current.getHinhAnh()).into(imgUseracc);
    }


    private void initControl() {
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountUesrActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnShoppinCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnWhish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMyPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderCompleteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void intiView() {
        btnAccount = (Button) findViewById(R.id.btnaccount);
        btnEditAccount = (Button)findViewById(R.id.btneditaccount);
        btnMyPurchase =(Button) findViewById(R.id.btnmypurchase);
        btnShoppinCart = (Button)findViewById(R.id.btnshoppincart);
        btnWhish = (Button)findViewById(R.id.nutyeuthich);
        userFullName = findViewById(R.id.userFullName);
        userGmail = findViewById(R.id.userGmail);
        imgUseracc = findViewById(R.id.imageView10);


    }
}