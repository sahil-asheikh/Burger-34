package com.begawoinc.burger34admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class image_upload extends AppCompatActivity {
    ImageView imageView;
    Uri uri;
    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        imageView = (ImageView) findViewById(R.id.image);

    }

    public void imageSelect(View view) {

        Intent photopicker = new Intent(Intent.ACTION_PICK);
        photopicker.setType("image/*");
        startActivityForResult(photopicker,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_FIRST_USER){
            assert data != null;
            uri= data.getData();
            imageView.setImageURI(uri);
        }
        else Toast.makeText(this,"You Haven't pick Image",Toast.LENGTH_SHORT).show();
    }

    public void UploadImage (){

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("Banner").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Banner Uploading...");
        progressDialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadBanner();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(image_upload.this,"Something Went Wrong!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btnUploadImage(View view) {

        UploadImage();
    }

        public void uploadBanner(){

            Banner banner = new Banner(
                   imageUrl);

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

            FirebaseDatabase.getInstance().getReference("Banner")
                    .child(myCurrentDateTime).setValue(banner).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(image_upload.this,"Banner Uploaded",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            });
    }
 }
