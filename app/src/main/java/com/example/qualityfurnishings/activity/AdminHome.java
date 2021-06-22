package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.AdminHomeFragment;
import com.example.qualityfurnishings.fragment.AdminProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.AdminHomeFrame, new AdminHomeFragment()).commit();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.adminbottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;

                switch(item.getItemId())
                {
                    case R.id.adminHome : temp = new AdminHomeFragment();
                    break;
                    case R.id.adminProfile : temp = new AdminProfileFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.AdminHomeFrame,temp).commit();

                return true;
            }
        });
    }
}