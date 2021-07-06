package com.example.qualityfurnishings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    public static final String UserIdPref = "UserPref" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Log.d("firebase", firebaseuserid);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);



//        Log.d("uu", userid);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String s1 = sharedPreferences.getString("usertype", "");
                if(s1.equals("user")){
                    startActivity(new Intent(SplashScreen.this, UserHome.class));
                }
                else if(s1.equals("admin")){
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