package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.DetailFragment;
import com.example.qualityfurnishings.fragment.UserCartFragment;
import com.example.qualityfurnishings.fragment.UserHomeFragment;
import com.example.qualityfurnishings.fragment.UserMenuFragment;
import com.example.qualityfurnishings.fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailActivity extends AppCompatActivity {
    BottomNavigationView usernavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        usernavigation = (BottomNavigationView)findViewById(R.id.userbottonnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.detailframe,new DetailFragment()).commit();
        usernavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
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
                getSupportFragmentManager().beginTransaction().replace(R.id.detailframe,temp).commit();
                return true;
            }
        });
    }

}