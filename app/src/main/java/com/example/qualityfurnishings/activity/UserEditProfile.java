package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.AdminModal;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;
import com.example.qualityfurnishings.model.UserTestModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import static android.content.ContentValues.TAG;

public class UserEditProfile extends AppCompatActivity {
    String name,email,phonenumber,password,confirmpassword,address,postalcode,province;
    EditText fullname,PhoneNumber,Password,ConfirmPassword,Address,PostalCode,Province;
    Button UserUpdateProfile;
    TextView Email;
    String userID;
    UserTestModal userTestModal;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String updateAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE",getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        ChangeLanguage(lang);
        updateAlert=getResources().getString(R.string.upu);
        /*if(lang.equals("fr")){

            //language.setText(english);
            Log.d(TAG, "onClick:lang");
            editor.putString("code","en");
            editor.commit();
            editor.apply();
            //restartActivity(getActivity());


        }
        if(lang.equals("en")){
            // language.setText(french);
            editor.putString("code","fr");
            editor.commit();
            editor.apply();
            //restartActivity(getActivity());

        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);
        Intent intent = getIntent();
        userTestModal = intent.getParcelableExtra("userdata");
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid","");
        fullname=(EditText)findViewById(R.id.etuserName);
        Email=(TextView)findViewById(R.id.etuserEmail);
        PhoneNumber=(EditText)findViewById(R.id.etuserPhoneNumber);
//        Password=(EditText)findViewById(R.id.etuserPassword);
//        ConfirmPassword=(EditText)findViewById(R.id.etuserCPassword);
        Address=(EditText)findViewById(R.id.etuserAddress);
        PostalCode=(EditText)findViewById(R.id.etuserPostalCode);
        Province=(EditText)findViewById(R.id.etuserProvince);
        UserUpdateProfile=(Button)findViewById(R.id.btuserUpdateProfile);
        Email.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"Sorry You cannot change your EmailID",Toast.LENGTH_LONG).show();

    }
});

        fullname.setText(userTestModal.getFullName());
        Email.setText(userTestModal.getEmail());
        PhoneNumber.setText(userTestModal.getPhoneNumber());
//        Password.setText(userTestModal.getPassword());
//        ConfirmPassword.setText(userTestModal.getPassword());
        Address.setText(userTestModal.getAddress());
        PostalCode.setText(userTestModal.getPostalcode());
        Province.setText(userTestModal.getProvince());
        UserUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuserprofile();
            }
        });


    }


    private void updateuserprofile() {
        name=fullname.getText().toString().trim();
        email=Email.getText().toString().trim();
        Log.d("bb", email);
        phonenumber=PhoneNumber.getText().toString().trim();
        password=userTestModal.getPassword();
        confirmpassword=userTestModal.getPassword();
        address=Address.getText().toString().trim();
        postalcode=PostalCode.getText().toString().trim();
        province=Province.getText().toString().trim();
        if (name.isEmpty()){
            fullname.setError("Please enter your full name");
            fullname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            Email.setError("Please enter your full name");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please provide valid email address!");
            Email.requestFocus();
            return;
        }
        if (phonenumber.isEmpty()){
            PhoneNumber.setError("Please enter your full name");
            PhoneNumber.requestFocus();
            return;
        }
//        if (password.isEmpty()){
//            Password.setError("Please enter your full name");
//            Password.requestFocus();
//            return;
//        }
//        if (confirmpassword.isEmpty()){
//            ConfirmPassword.setError("Please enter your full name");
//            ConfirmPassword.requestFocus();
//            return;
//        }
//        if(!password.equals(confirmpassword)) {
//
//            Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
//            return;
//        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        UserTestModal userModal = new UserTestModal(name, email,phonenumber,password,address,postalcode,province);
        databaseReference.child("users").child(userID).setValue(userModal);

        Toast.makeText(getApplicationContext(), updateAlert, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),UserHome.class);
        startActivity(intent);

    }

    public void ChangeLanguage(String language){

        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE",getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
    }
}