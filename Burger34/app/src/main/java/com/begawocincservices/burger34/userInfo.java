package com.begawocincservices.burger34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userInfo extends AppCompatActivity {

    EditText userName_userInfo, userNumber_userInfo, userAddress_userInfo;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        userName_userInfo = findViewById(R.id.userName_userInfo);
        userNumber_userInfo = findViewById(R.id.userNumber_userInfo);
        userAddress_userInfo = findViewById(R.id.userAddress_userInfo);
        saveBtn = findViewById(R.id.saveBtn);


//              first time start
//                check if first time start
        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = sharedPreferences.getString("FirstTimeInstall", "");

        if (firstTime.equals("Yes")) {

//                  if app is opened for the first time
            Intent intent = new Intent(userInfo.this, MainActivity.class);
            startActivity(intent);
            //                  to end this activity
            userInfo.this.finish();

        } else {
//                    else store Yes in sharedPreference
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }


//      onclick listener for save button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              to store user info
                if (TextUtils.isEmpty(userName_userInfo.getText().toString())){
                    Toast.makeText(userInfo.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userNumber_userInfo.getText().toString())){
                    Toast.makeText(userInfo.this, "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userAddress_userInfo.getText().toString())){
                    Toast.makeText(userInfo.this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
                } else {

                    final String userName = userName_userInfo.getText().toString();
                    final String userNumber = userNumber_userInfo.getText().toString();
                    final String userAddress = userAddress_userInfo.getText().toString();

//                  user info save method invoked
                    saveInfo(userName, userNumber, userAddress);

//                  intent to move on main activity
                    Intent intent = new Intent(userInfo.this, MainActivity.class);
                    startActivity(intent);

//                  to end this activity
                    userInfo.this.finish();

                }


            }
        });
    }

//  method to store user info
    private void saveInfo(String userName, String userNumber, String userAddress) {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.putString("userNumber", userNumber);
        editor.putString("userAddress", userAddress);
        editor.apply();

        Toast.makeText(userInfo.this, "Details Saved", Toast.LENGTH_SHORT).show();
    }
}