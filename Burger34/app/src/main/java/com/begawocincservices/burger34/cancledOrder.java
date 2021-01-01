package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cancledOrder extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancled_order);

        goBack = findViewById(R.id.backToHome);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cancledOrder.this, MainActivity.class);
                startActivity(intent);
                cancledOrder.this.finish();
            }
        });

    }
}