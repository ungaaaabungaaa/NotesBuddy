package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class uploadsem3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadsem3);
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
                Intent intent = new Intent(uploadsem3.this, uploadsem3m3.class);
                startActivity(intent);
                break;
            case R.id.ms:
                intent = new Intent(uploadsem3.this, uploadsem3ms.class);
                startActivity(intent);
                break;
            case R.id.mo:
                intent = new Intent(uploadsem3.this, uploadsem3mo.class);
                startActivity(intent);
                break;
            case R.id.cs:
                intent = new Intent(uploadsem3.this, uploadsem3cs.class);
                startActivity(intent);
                break;
            case R.id.ad:
                intent = new Intent(uploadsem3.this, uploadsem3ad.class);
                startActivity(intent);
                break;
            case R.id.co:
                intent = new Intent(uploadsem3.this, uploadsem3co.class);
                startActivity(intent);
                break;
        }
    }
}