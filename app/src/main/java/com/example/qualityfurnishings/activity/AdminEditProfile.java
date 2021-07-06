package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.qualityfurnishings.R;

public class AdminEditProfile extends AppCompatActivity {
    EditText name,email,password,cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String stName = b.getString("name");
        String stEmail = b.getString("email");
        String stPassword = b.getString("password");
        name=(EditText)findViewById(R.id.etName);
        email=(EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etPassword);
        cpassword=(EditText)findViewById(R.id.etCPassword);
        name.setText(stName);
        email.setText(stEmail);
        password.setText(stPassword);
        cpassword.setText(stPassword);



    }
}