package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class orderPlaced extends AppCompatActivity {

    Button goBack, contactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        goBack = findViewById(R.id.backToHome);
        contactUs = findViewById(R.id.contactUs);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(orderPlaced.this, MainActivity.class);
                startActivity(intent);

                orderPlaced.this.finish();
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(orderPlaced.this, contactUsOwner.class);
                startActivity(intent);
                orderPlaced.this.finish();
            }
        });

    }
}