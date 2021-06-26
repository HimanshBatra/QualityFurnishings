package com.example.qualityfurnishings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.activity.AdminHome;

import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private TextView textview1, registerUser,signin;
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword,editTextPhoneNumber;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        textview1 = (TextView) findViewById(R.id.textview1);





        editTextFullName = (EditText) findViewById(R.id.etName);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.etCPassword);
        editTextPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
        signin=(TextView) findViewById(R.id.tvSignIn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        registerUser = (Button) findViewById(R.id.btRegister);
      //  registerUser.setBackgroundColor(Color.parseColor("#1F2633"));

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }




    private void registerUser() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (fullName.isEmpty()){
            editTextFullName.setError("Please enter your full name");
            editTextFullName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Please enter your phone number");
            editTextPhoneNumber.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email address!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Please confirm your password");
            editTextConfirmPassword.requestFocus();
            return;
        }
        if(!password.equals(confirmPassword)){
            editTextPassword.requestFocus();
            editTextConfirmPassword.requestFocus();
            Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
             return;
        }




        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            User user = new User(fullName, email,phoneNumber,password);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {


                                     

                                       Intent intent = new Intent(getApplicationContext(),UserHome.class);
                                       startActivity(intent);


                                        Toast.makeText(SignUp.this, "User is registered successfully!", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(SignUp.this, "Failed to register! Please Try again!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });



                        }else {
                            Toast.makeText(SignUp.this, "Failed to register!", Toast.LENGTH_LONG).show();


                        }
                    }
                });


    }
}