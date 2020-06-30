package com.example.notesbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class sem6pe extends AppCompatActivity implements  ImageAdapter.OnItemClickListener  {


    androidx.recyclerview.widget.RecyclerView RecyclerView;
    ImageAdapter mAdapter;
    private List<Upload> mUploads;
    FirebaseStorage mStorage;
    DatabaseReference mDatabaseRef;
    ValueEventListener mDBListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem6pe);

        RecyclerView = findViewById(R.id.r1);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mUploads = new ArrayList<>();
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sem6pe");
        mDatabaseRef.keepSynced(true);
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter(sem6pe.this, mUploads);
                mAdapter.setOnItemClickListener(sem6pe.this);
                RecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(sem6pe.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(String position) {
        Intent intent =new Intent(sem6pe.this,zoomimage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("eye",position);
        startActivity(intent);
    }

    @Override
    public void onWhatEverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}