package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class aboutUsDeveloper extends AppCompatActivity {

    Button instagram, linkedIn, email, test_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_developer);

        getSupportActionBar().hide();

        instagram = findViewById(R.id.instagram);
        linkedIn = findViewById(R.id.linkedIn);
        email = findViewById(R.id.email);
        test_btn = findViewById(R.id.test_btn);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkedInURL = Uri.parse("https://www.instagram.com/sahil.asheikh/");
                Intent intentLinkedInURL = new Intent(Intent.ACTION_VIEW, linkedInURL);
                startActivity(intentLinkedInURL);
            }
        });

        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkedInURL = Uri.parse("https://www.linkedin.com/in/sahilasheikh/");
                Intent intentLinkedInURL = new Intent(Intent.ACTION_VIEW, linkedInURL);
                startActivity(intentLinkedInURL);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                codes for email
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:supreme.begawo@gmail.com?subject=" + Uri.encode("Hi, I want to contact you regarding app development") + "&body=" + Uri.encode(""));
                intent.setData(data);
                startActivity(intent);
            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}