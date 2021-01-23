package com.begawoinc.burger34admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class image_upload extends AppCompatActivity {
    ImageView imageView;
    Uri uri;
    String imageUrl;
    EditText coupon_code, off_percentage;
    Button updateCoupon_btn;

    private DatabaseReference rootDatabaseref;
    private DatabaseReference rootDatabaseref_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        imageView = (ImageView) findViewById(R.id.image);
        updateCoupon_btn = findViewById(R.id.Update_Coupon_Code);
        coupon_code = findViewById(R.id.coupon_code);
        off_percentage = findViewById(R.id.off_percentage);
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("couponcode");
        rootDatabaseref_percentage = FirebaseDatabase.getInstance().getReference().child("offPercentage");


//      update coupon code
        updateCoupon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = coupon_code.getText().toString();
                String off_percentage_string = off_percentage.getText().toString();
                int percentage = Integer.parseInt(off_percentage_string);

//                if (TextUtils.isEmpty(userName_userInfo.getText().toString())){

                if (TextUtils.isEmpty(coupon_code.getText().toString())){
                    Toast.makeText(image_upload.this, "Please Enter Coupon Code", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(off_percentage.getText().toString())){
                    Toast.makeText(image_upload.this, "Please Enter Off Percentage", Toast.LENGTH_SHORT).show();
                } else{
                    updateCouponCode_firebase(code);
                    update_percentage_offer(percentage);
                }

            }
        });

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

//  methods for banners
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

//  method to update the coupon code
    public void updateCouponCode_firebase(String code){
        rootDatabaseref.setValue(code).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(image_upload.this, "Coupon Code is Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(image_upload.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

//  method to update the offer percentage
    public void update_percentage_offer(int percentage){
        rootDatabaseref_percentage.setValue(percentage);
    }

 }
