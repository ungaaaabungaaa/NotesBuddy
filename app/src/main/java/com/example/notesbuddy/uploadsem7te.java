package com.example.notesbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.asksira.bsimagepicker.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class uploadsem7te extends AppCompatActivity  implements BSImagePicker.OnSingleImageSelectedListener,  BSImagePicker.ImageLoaderDelegate,BSImagePicker.OnSelectImageCancelledListener,ImageAdapter.OnItemClickListener {


    EditText uEditTextFileName;
    ImageView uImageView;
    ProgressBar uProgressBar;
    Uri mImageUri;
    StorageReference uStorageRef;
    DatabaseReference uDatabaseRef;
    StorageTask uUploadTask;
    FloatingActionButton uButtonUpload;
    Uri compreeseduri;
    File file;
    FloatingActionButton floatingActionButton;
    androidx.recyclerview.widget.RecyclerView RecyclerView;
    ImageAdapter mAdapter;
    FirebaseStorage mStorage;
    DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ValueEventListener mDBListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadsem7te);
        uImageView = findViewById( R.id.image_view);
        LinearLayout constraintLayout = findViewById( R.id.layout );
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        uEditTextFileName = findViewById( R.id.edit_text_file_name );
        uProgressBar = findViewById( R.id.progress_bar );
        uStorageRef = FirebaseStorage.getInstance().getReference("sem7te");
        uDatabaseRef = FirebaseDatabase.getInstance().getReference( "sem7te");
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        uButtonUpload = findViewById(R.id.button_upload);
        uButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uUploadTask != null && uUploadTask.isInProgress()) {
                    Toast.makeText(uploadsem7te.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        RecyclerView = findViewById(R.id.r1);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sem7te");
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
                mAdapter = new ImageAdapter(uploadsem7te.this, mUploads);
                mAdapter.setOnItemClickListener(uploadsem7te.this);
                RecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(uploadsem7te.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void openFileChooser() {
        BSImagePicker singleSelectionPicker = new BSImagePicker.Builder("com.example.filepicker.fileprovider")
                .setMaximumDisplayingImages(23)
                .setSpanCount(3)
                .setGridSpacing(Utils.dp2px(2))
                .setPeekHeight(Utils.dp2px(360))
                .hideCameraTile()
                .build();
        singleSelectionPicker.show(getSupportFragmentManager(), "hey");
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType( cR.getType(uri));
    }
    private void uploadFile() {
        if (compreeseduri!=null) {
            uProgressBar.setVisibility(View.VISIBLE);
            StorageReference fileReference = uStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            uUploadTask = fileReference.putFile(compreeseduri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            Upload upload = new Upload(uEditTextFileName.getText().toString().trim(),downloadUrl.toString());
                            String uploadId = uDatabaseRef.push().getKey();
                            uDatabaseRef.child(uploadId).setValue(upload);
                            uProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(uploadsem7te.this, "Upload successful", Toast.LENGTH_LONG).show();
                            uProgressBar.setVisibility(View.INVISIBLE);
                            file.delete();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(uploadsem7te.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            uProgressBar.setVisibility(View.INVISIBLE);
                            file.delete();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Picasso.get().load(imageUri).into(ivImage);
    }
    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        CropImage.activity(uri)
                .start(uploadsem7te.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult( data );
            if (resultCode == RESULT_OK) {
                mImageUri = result.getUri();
                uImageView.setImageURI(mImageUri);
                file=new File(SiliCompressor.with(this).compress(FileUtils.getPath(this,mImageUri),new File(this.getCacheDir(),"temp")));
                compreeseduri=Uri.fromFile(file);
            } else
            {
                file.delete();
                Toast toast = Toast.makeText(uploadsem7te.this, "No Image selected", Toast.LENGTH_LONG); toast.show();
            }
            if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(uploadsem7te.this, "Error : " + error, Toast.LENGTH_LONG).show();
                file.delete();
            }
        }
    }
    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {
        file.delete();
        Toast toast = Toast.makeText(uploadsem7te.this, "No Image selected", Toast.LENGTH_LONG); toast.show();
    }


    @Override
    public void onItemClick(String position) {
        Intent intent =new Intent(uploadsem7te.this,zoomimage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("eye",position);
        startActivity(intent);
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Reported", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(uploadsem7te.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
