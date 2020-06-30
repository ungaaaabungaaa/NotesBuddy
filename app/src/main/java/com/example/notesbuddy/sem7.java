package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sem7 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem7);
        Button button1 = findViewById(R.id.ir);
        Button button2 = findViewById(R.id.te);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ir:
                Intent intent = new Intent(sem7.this, sem7ir.class);
                startActivity(intent);
                break;
            case R.id.te:
                intent = new Intent(sem7.this, sem7te.class);
                startActivity(intent);
                break;
        }
    }
}