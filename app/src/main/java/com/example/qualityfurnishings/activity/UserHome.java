package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.UserCartFragment;
import com.example.qualityfurnishings.fragment.UserHomeFragment;
import com.example.qualityfurnishings.fragment.UserMenuFragment;
import com.example.qualityfurnishings.fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class UserHome extends AppCompatActivity {
    BottomNavigationView usernavigation;
    public static final String UserIdPref = "UserPref" ;
    public static final String Userid = "userid";
    SharedPreferences sharedpreferences;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String q,c,y;
    String alertBox;

    public void onBackPressed(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserScreen", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("lastscreen", "");
        if(s1.equals("HOME")){
            AlertDialog.Builder builder = new AlertDialog.Builder(UserHome.this);
            builder.setTitle(q);
            builder.setMessage(alertBox);

            // add a button
            builder.setPositiveButton(y, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();

                }
            });
            builder.setNegativeButton(c, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;

                }

            });
            builder.show();
        }
        else {
            showHomeScreen();
        }


    }

    private void showHomeScreen() {
        usernavigation.setSelectedItemId(R.id.userHome);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE",getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        q=getResources().getString(R.string.quit);
        alertBox=getResources().getString(R.string.alertDialogForQuit);
        c=getResources().getString(R.string.cancel);
        y=getResources().getString(R.string.yes);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        firebaseAuth= FirebaseAuth.getInstance();
//        FirebaseMessaging.getInstance().subscribeToTopic("all");
        String firebaseuserid = firebaseAuth.getCurrentUser().getUid();
        sharedpreferences = getApplicationContext().getSharedPreferences(UserIdPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Userid, firebaseuserid);
        editor.commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserHomeFragment()).commit();
        usernavigation = (BottomNavigationView)findViewById(R.id.userbottonnav);
        usernavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;
                switch (item.getItemId()){
                    case R.id.userHome : temp = new UserHomeFragment();
                        break;
                    case R.id.userProfile : temp = new UserProfileFragment();
                        break;
                    case R.id.userCart : temp = new UserCartFragment();
                        break;
                    case R.id.userLogout : temp = new UserMenuFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.UserHomeFrame,temp).commit();
                return true;
            }
        });
    }

    public void showUserProfile() {
        usernavigation.setSelectedItemId(R.id.userProfile);
    }
}