package com.example.notesbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class uploadmain extends AppCompatActivity {
    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private TextView loginRegBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadmain);
        mAuth = FirebaseAuth.getInstance();
        loginEmailText = findViewById(R.id.reg_email);
        loginPassText = findViewById(R.id.reg_confirm_pass);
        loginBtn = findViewById(R.id.login_btn);
        loginRegBtn = findViewById(R.id.login_reg_btn);
        loginProgress = findViewById(R.id.login_progress);
        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(uploadmain.this, "Ester Egg Hint", Toast.LENGTH_LONG).show();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();
                if (TextUtils.isEmpty(loginEmail) && TextUtils.isEmpty(loginPass)) {
                    Toast.makeText(uploadmain.this, "Fields Are Empty", Toast.LENGTH_LONG).show();
                } else {
                    if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                        loginProgress.setVisibility(View.VISIBLE);
                        mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendToMain();
                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(uploadmain.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                                }
                                loginProgress.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
            }
        });
    }
    private void sendToMain() {
        Intent mainIntent = new Intent(this, uploadsemsel.class);
        startActivity(mainIntent);
        finish();
    }
}