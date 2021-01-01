package com.begawocincservices.burger34;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MainActivity_KEY = "com.begawoinc.burger34_MainActivity_KEY";

    Button btn_product001, btn_product002, btn_product003, btn_product004, btn_product005;
    private RecyclerView recyclerView;
    List<Banner> mybanner;
    Banner mBanner;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    //                Owner login information
    final String txt_name = "Prajayot Ramteke";
    final String txt_email = "burger34official@gmail.com";
    final String txt_password = "Burger34";

    //                user login info
    final String txt_UserName = "Customer";
    final String txt_UserEmail = "customer.burger34@gmail.com";
    final String txt_UserPassword = "customer login";

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      to hide the action bar
        getSupportActionBar().hide();

//      popup of chandrapur
        SharedPreferences sharedPreferences = getSharedPreferences("locationInfo", MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);

        if (firstStart) {
            showDialogPopup();
        }

//      find view by id
        recyclerView = findViewById(R.id.card);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        RecyclerView.LayoutManager Manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(Manager);

        mybanner = new ArrayList<>();
        mBanner= new Banner();

        mybanner.add(mBanner);

        final MyAdapter myAdapter =new MyAdapter(MainActivity.this,mybanner);
        recyclerView.setAdapter(myAdapter);

        databaseReference =FirebaseDatabase.getInstance().getReference("Banner");

        progressDialog.show();
        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mybanner.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    Banner banner= itemSnapshot.getValue(Banner.class);

                    mybanner.add(banner);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
            }
        });


        btn_product001 = findViewById(R.id.buyProduct001);
        btn_product002 = findViewById(R.id.buyProduct002);
        btn_product003 = findViewById(R.id.buyProduct003);
        btn_product004 = findViewById(R.id.buyProduct004);
        btn_product005 = findViewById(R.id.buyProduct005);

        auth = FirebaseAuth.getInstance();
//        customerLogin(txt_UserEmail, txt_UserPassword);





//      products on click listener
//      Button of Product 001
        btn_product001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, productDetail001.class);
                startActivity(intent);
            }
        });

//      Button of Product 002
        btn_product002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, product002.class);
                startActivity(intent);
            }
        });

//      Button of Product 003
        btn_product003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, product003.class);
                startActivity(intent);
            }
        });

//      Button of Product 004
        btn_product004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, product004.class);
                startActivity(intent);
            }
        });

//      Button of Product 005
        btn_product005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, product005.class);
                startActivity(intent);
            }
        });


    }


    //  toolbar button
    public void infoImg(View view) {
        Intent intent = new Intent(MainActivity.this, contactUsOwner.class);
        startActivity(intent);
    }

    public void cart(View view) {
        Intent intent = new Intent(MainActivity.this, ConformOrder.class);
        startActivity(intent);
    }

    public void userInfo(View view) {
        Intent intent = new Intent(MainActivity.this, userInfo.class);
        startActivity(intent);
    }

    //  method for show dialogue
    private void showDialogPopup() {
        new AlertDialog.Builder(this)
                .setTitle("Welcome to Burger 34")
                .setMessage("We are very sorry but, online delivery is only available in Chandrapur, so if you are not live in Chandrapur then it is not possible to deliver your Orders")
                .setPositiveButton("Yes, I live in Chandrapur", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No, I don't live in Chandrapur", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, locationError.class);
                        startActivity(intent);
                    }
                })
                .create().show();
        SharedPreferences sharedPreferences = getSharedPreferences("locationInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putBoolean("firstStart", false);
        editor.apply();
    }

    // login method
    private void customerLogin(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else
                        {
                            Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}