package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.UserLoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Locale;

public class UserUpdatePassword extends AppCompatActivity {
    EditText email;
    Button buttonsend;
    String Semail;

    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String checkingValidityEmail;
    String mailLink;
    String c,y,a;
    String userNotexists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_password);
        buttonsend = (Button)findViewById(R.id.btMailSent);
        email =(EditText)findViewById(R.id.etMail);
        mAuth = FirebaseAuth.getInstance();
        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semail = email.getText().toString();
                if(Patterns.EMAIL_ADDRESS.matcher(Semail).matches()){

                    //auth.fetchProvidersForEmail(email);

                    CheckingWhetherEmailIsPresentOrNot(v);
                }
                else
                {
                    email.requestFocus();
                    email.setError(checkingValidityEmail); }
            }
        });
        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
       // editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        checkingValidityEmail=getResources().getString(R.string.eve);
        c=getResources().getString(R.string.cancel);
        y=getResources().getString(R.string.yes);
        a=getResources().getString(R.string.alert);
        userNotexists=getResources().getString(R.string.une);
        mailLink=getResources().getString(R.string.mailLink);



    }

    private void CheckingWhetherEmailIsPresentOrNot(View v) {
        mAuth.fetchSignInMethodsForEmail(Semail)
                .addOnCompleteListener(
                        new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean validation = !task.getResult().getSignInMethods().isEmpty();
                                if (!validation) {
                                    Toast.makeText(getApplicationContext(), userNotexists, Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    AlertDialog.Builder builder
                                            = new AlertDialog
                                            .Builder(UserUpdatePassword.this);
                                    builder.setMessage(mailLink);

                                    // Set Alert Title
                                    builder.setTitle(a);
                                    builder.setPositiveButton(y, new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mAuth.sendPasswordResetEmail(Semail)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull  Task<Void> task) {

                                                            Intent intent = new Intent(getApplicationContext(),UserHome.class);
                                                            startActivity(intent);

                                                        }
                                                    });
                                        }
                                    });
                                    builder.setNegativeButton(c, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(getApplicationContext(),UserHome.class);
                                            startActivity(intent);
                                        }
                                    });
                                    builder.show();


                                }

                            }
                        });
    }
}