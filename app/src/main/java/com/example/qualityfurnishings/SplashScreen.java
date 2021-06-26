package com.example.qualityfurnishings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String s1 = sharedPreferences.getString("usertype", "");
                if(s1=="user"){
                    startActivity(new Intent(SplashScreen.this, UserHome.class));
                }
                else if(s1 == "admin"){
                    startActivity(new Intent(SplashScreen.this, AdminHome.class));
                }
                else{
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
                finish();

            }
        },4000);

    }
}