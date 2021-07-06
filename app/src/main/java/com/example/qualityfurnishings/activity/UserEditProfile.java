package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.ProductModal;
import com.example.qualityfurnishings.model.UserTestModal;

public class UserEditProfile extends AppCompatActivity {
    String name,email,phonenumber,password,confirmpassword,address,postalcode,province;
    EditText fullname,Email,PhoneNumber,Password,ConfirmPassword,Address,PostalCode,Province;
    Button UserUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);
        Intent intent = getIntent();
        UserTestModal userTestModal = intent.getParcelableExtra("userdata");
        fullname=(EditText)findViewById(R.id.etuserName);
        Email=(EditText)findViewById(R.id.etuserEmail);
        PhoneNumber=(EditText)findViewById(R.id.etuserPhoneNumber);
        Password=(EditText)findViewById(R.id.etuserPassword);
        ConfirmPassword=(EditText)findViewById(R.id.etuserCPassword);
        Address=(EditText)findViewById(R.id.etuserAddress);
        PostalCode=(EditText)findViewById(R.id.etuserPostalCode);
        Province=(EditText)findViewById(R.id.etuserProvince);
        UserUpdateProfile=(Button)findViewById(R.id.btuserUpdateProfile);


        fullname.setText(userTestModal.getFullName());
        Email.setText(userTestModal.getEmail());
        PhoneNumber.setText(userTestModal.getPhoneNumber());
        Password.setText(userTestModal.getPassword());
        ConfirmPassword.setText(userTestModal.getPassword());
        Address.setText(userTestModal.getAddress());
        PostalCode.setText(userTestModal.getPostalcode());
        Province.setText(userTestModal.getProvince());

    }
}