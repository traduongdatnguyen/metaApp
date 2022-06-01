package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meta.R;

public class EditHoSoActivity extends AppCompatActivity {
    Toolbar toolbar;
    String title= "";
    String inFoUser= "";
    TextView toolbar_title;
    EditText edithientai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ho_so);
        title = getIntent().getStringExtra("title");
        inFoUser = String.valueOf(getIntent().getStringExtra("infoUser"));
        initView();

        ActionToolBar();
        initData();
    }

    private void initData() {
        edithientai.setText(inFoUser+"");
    }

    private void initView() {
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toobar);
        edithientai = findViewById(R.id.edithientai);
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
        toolbar_title.setText(title);
    }
}