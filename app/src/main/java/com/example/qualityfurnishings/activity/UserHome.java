package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.UserCartFragment;
import com.example.qualityfurnishings.fragment.UserHomeFragment;
import com.example.qualityfurnishings.fragment.UserMenuFragment;
import com.example.qualityfurnishings.fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class UserHome extends AppCompatActivity {
    BottomNavigationView usernavigation;
    public static final String UserIdPref = "UserPref" ;
    public static final String Userid = "userid";
    SharedPreferences sharedpreferences;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
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