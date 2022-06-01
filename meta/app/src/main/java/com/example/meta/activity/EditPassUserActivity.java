package com.example.meta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meta.R;

public class EditPassUserActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btneditpassnew;
    String title= "";
    TextView toolbar_title;
    EditText editpasshientai,editpassnew,editpassress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_user);
        title = getIntent().getStringExtra("title");
        initView();
        AcctionToolBar();
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
        toolbar_title.setText(title);
    }

    private void initView() {
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toobar);
        editpasshientai = findViewById(R.id.editpasshientai);
        editpassnew = findViewById(R.id.editpassnew);
        editpassress = findViewById(R.id.editpassress);
        btneditpassnew = findViewById(R.id.btneditpassnew);
    }
}