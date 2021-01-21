package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        Logo logo = new Logo();
        logo.start();
    }


    private class Logo extends Thread{
        public void run(){
            try{
                sleep(1300);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent= new Intent(SplashActivity.this, register_user_info.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}