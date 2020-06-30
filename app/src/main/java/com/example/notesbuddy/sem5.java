package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sem5 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem5);
        Button button1 = findViewById(R.id.ti);
        Button button2 = findViewById(R.id.da);
        Button button3 = findViewById(R.id.vi);
        Button button4 = findViewById(R.id.hp);
        Button button5 = findViewById(R.id.ms);
        Button button6= findViewById(R.id.wn);
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
            case R.id.ti:
                Intent intent = new Intent(sem5.this, sem5ti.class);
                startActivity(intent);
                break;
            case R.id.da:
                intent = new Intent(sem5.this, sem5da.class);
                startActivity(intent);
                break;
            case R.id.vi:
                intent = new Intent(sem5.this, sem5vi.class);
                startActivity(intent);
                break;
            case R.id.hp:
                intent = new Intent(sem5.this, sem5hp.class);
                startActivity(intent);
                break;
            case R.id.ms:
                intent = new Intent(sem5.this, sem5ms.class);
                startActivity(intent);
                break;
            case R.id.wn:
                intent = new Intent(sem5.this, sem5wn.class);
                startActivity(intent);
                break;
        }
    }
}