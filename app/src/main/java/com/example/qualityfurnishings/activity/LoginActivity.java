package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.AdminLoginFragment;
import com.example.qualityfurnishings.fragment.UserLoginFragment;

public class LoginActivity extends AppCompatActivity {
//    TextView user,admin;
public void onBackPressed(){
    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
    builder.setTitle("Quit");
    builder.setMessage("Do You want to close the application.");

    // add a button
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finishAffinity();

        }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            return;

        }

    });
    builder.show();

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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