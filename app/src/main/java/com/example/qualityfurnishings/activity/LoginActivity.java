package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.AdminLoginFragment;
import com.example.qualityfurnishings.fragment.UserLoginFragment;

public class LoginActivity extends AppCompatActivity {
//    TextView user,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();
//        user = (TextView)findViewById(R.id.tvUser);
//        admin = (TextView)findViewById(R.id.tvAdmin);
//        user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();
//            }
//        });
//        admin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new AdminLoginFragment()).commit();
//            }
//        });

    }
}