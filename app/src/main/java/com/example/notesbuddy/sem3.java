package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sem3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem3);
        Button button1 = findViewById(R.id.m3);
        Button button2 = findViewById(R.id.ms);
        Button button3 = findViewById(R.id.mo);
        Button button4 = findViewById(R.id.cs);
        Button button5 = findViewById(R.id.ad);
        Button button6= findViewById(R.id.co);
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
            case R.id.m3:
                Intent intent = new Intent(sem3.this, m3.class);
                startActivity(intent);
                break;
            case R.id.ms:
                intent = new Intent(sem3.this, sem3ms.class);
                startActivity(intent);
                break;
            case R.id.mo:
                intent = new Intent(sem3.this, sem3mo.class);
                startActivity(intent);
                break;
            case R.id.cs:
                intent = new Intent(sem3.this, sem3cs.class);
                startActivity(intent);
                break;

            case R.id.ad:
                intent = new Intent(sem3.this, sem3ad.class);
                startActivity(intent);
                break;

            case R.id.co:
                intent = new Intent(sem3.this, sem3co.class);
                startActivity(intent);
                break;


        }
    }

}