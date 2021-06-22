package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.UserCartFragment;
import com.example.qualityfurnishings.fragment.UserHomeFragment;
import com.example.qualityfurnishings.fragment.UserMenuFragment;
import com.example.qualityfurnishings.fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHome extends AppCompatActivity {
    BottomNavigationView usernavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        getSupportActionBar().hide();
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
}