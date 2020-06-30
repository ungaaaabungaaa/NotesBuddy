package com.example.notesbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class uploadsem6 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadsem6);
        Button button1 = findViewById(R.id.plc);
        Button button2 = findViewById(R.id.pe);
        Button button3 = findViewById(R.id.camd);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plc:
                Intent intent = new Intent(this, uploadsem6plc.class);
                startActivity(intent);
                break;
            case R.id.pe:
                intent = new Intent(this, uploadsem6pe.class);
                startActivity(intent);
                break;
            case R.id.camd:
                intent = new Intent(this, uploadsem6camd.class);
                startActivity(intent);
                break;
        }
    }
}