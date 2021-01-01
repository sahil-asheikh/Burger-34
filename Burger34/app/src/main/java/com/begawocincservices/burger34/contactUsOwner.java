package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contactUsOwner extends AppCompatActivity {

    Button phone, location, instagram, aboutDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_owner);

        getSupportActionBar().hide();

        phone = findViewById(R.id.phoneNum);
        location = findViewById(R.id.mapView);
        instagram = findViewById(R.id.instagram);
        aboutDeveloper = findViewById(R.id.aboutDeveloper);

        aboutDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contactUsOwner.this, aboutUsDeveloper.class);
                startActivity(intent);
            }
        });


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7558510923"));
                startActivity(intent);
            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkedInURL = Uri.parse("https://maps.app.goo.gl/zhar81fhyLUhn6Vd9");
                Intent intentLinkedInURL = new Intent(Intent.ACTION_VIEW, linkedInURL);
                startActivity(intentLinkedInURL);
            }
        });


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkedInURL = Uri.parse("https://www.instagram.com/burger.34/");
                Intent intentLinkedInURL = new Intent(Intent.ACTION_VIEW, linkedInURL);
                startActivity(intentLinkedInURL);
            }
        });


    }
}