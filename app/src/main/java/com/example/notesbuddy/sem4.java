package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sem4 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem4);


        Button button1 = findViewById(R.id.m4);
        Button button2 = findViewById(R.id.fmm);
        Button button3 = findViewById(R.id.m);
        Button button4 = findViewById(R.id.mt);
        Button button5 = findViewById(R.id.tom);
        Button button6= findViewById(R.id.im);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m4:
                Intent intent = new Intent(sem4.this, m4.class);
                startActivity(intent);
                break;
            case R.id.fmm:
                intent = new Intent(sem4.this, sem4fmm.class);
                startActivity(intent);
                break;
            case R.id.m:
                intent = new Intent(sem4.this, sem4m.class);
                startActivity(intent);
                break;
            case R.id.mt:
                intent = new Intent(sem4.this, sem4mt.class);
                startActivity(intent);
                break;
            case R.id.tom:
                intent = new Intent(sem4.this, sem4tom.class);
                startActivity(intent);
                break;
            case R.id.im:
                intent = new Intent(sem4.this, sem4im.class);
                startActivity(intent);
                break;
        }
    }
}