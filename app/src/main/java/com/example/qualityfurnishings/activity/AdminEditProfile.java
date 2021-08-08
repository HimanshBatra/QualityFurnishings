package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.AdminHomeFragment;
import com.example.qualityfurnishings.fragment.AdminProfileFragment;
import com.example.qualityfurnishings.model.AdminModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class AdminEditProfile extends AppCompatActivity {
    EditText name,email,password,cpassword;
    Button updateProfile;
    String stName,stEmail,stPassword;
    String uName,uEmail,uPassword,uCpassword;
    String updateAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        stName = b.getString("name");
        stEmail = b.getString("email");
        stPassword = b.getString("password");
        name=(EditText)findViewById(R.id.etName);
        email=(EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etPassword);
        cpassword=(EditText)findViewById(R.id.etCPassword);
        updateProfile=(Button) findViewById(R.id.btUpdateProfile);

        name.setText(stName);
        email.setText(stEmail);
        password.setText(stPassword);
        cpassword.setText(stPassword);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        updateAlert=getResources().getString(R.string.upu);
        
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             updateAdmin();
            }
        });




    }

    private void updateAdmin() {
        uName = name.getText().toString().trim();
        uEmail = email.getText().toString().trim();
        uPassword = password.getText().toString().trim();
        uCpassword = cpassword.getText().toString().trim();
//        Log.d("hh", uName);
        if (uName.isEmpty()){
            name.setError("Please enter your full name");
            name.requestFocus();
            return;
        }
        if (uEmail.isEmpty()){
            email.setError("Please enter your full name");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()){
            email.setError("Please provide valid email address!");
            email.requestFocus();
            return;
        }
        if (uPassword.isEmpty()){
            password.setError("Please enter your full name");
            password.requestFocus();
            return;
        }
        if (uCpassword.isEmpty()){
            cpassword.setError("Please enter your full name");
            cpassword.requestFocus();
            return;
        }
        if(!uPassword.equals(uCpassword)){
            password.requestFocus();
            cpassword.requestFocus();
            Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        AdminModal admin = new AdminModal(uName, uPassword,uEmail);
        databaseReference.child("FurnitureCategory").child("Admin").setValue(admin);
        Toast.makeText(getApplicationContext(), updateAlert, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),AdminHome.class);
        startActivity(intent);

//        Log.d("hh", uName);
//        Log.d("hh1", uEmail);
//        Log.d("hh2", uPassword);
//        Log.d("hh3", uCpassword);

    }
}