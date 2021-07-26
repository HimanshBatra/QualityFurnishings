package com.example.qualityfurnishings.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.SignUp;
import com.example.qualityfurnishings.activity.UserHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

import static android.content.ContentValues.TAG;


public class UserLoginFragment extends Fragment {
    TextView language;
    EditText email,password;
    Button login;
    TextView user,admin,forgotPassword,signup;
    SharedPreferences sharedPreferences;

    Context context;
    Resources resources;
    private FirebaseAuth firebaseAuth;
    public static final String MyPREFERENCES = "LoginPref" ;
    public static final String UserType = "usertype";
    //SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    String english = "English";
    String french = "French";


    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        ChangeLanguage(lang);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        email=view.findViewById(R.id.etEmail);
        forgotPassword = view.findViewById(R.id.tvForgetPassword);
        password=view.findViewById(R.id.etPssword);
        login=view.findViewById(R.id.btUserLogin);
        login.setBackgroundColor(Color.parseColor("#1F2633"));
        user = view.findViewById(R.id.tvUser);
        admin = view.findViewById(R.id.tvAdmin);
        signup = view.findViewById(R.id.tvSignup);

        language = view.findViewById(R.id.textViewLanguage);


        String lang = sharedPreferences.getString("code","en");
        if(lang.equals("en")){
            language.setText(french);
        }else if(lang.equals("fr")){
            language.setText(english);
        }


        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String screenLanguage = language.getText().toString();
                if(screenLanguage.equals("French")){

                   language.setText(english);
                    Log.d(TAG, "onClick:lang");
                    editor.putString("code","fr");
                    editor.commit();
                    editor.apply();
                    restartActivity(getActivity());


                }
                if(screenLanguage.equals("English")){
                    language.setText(french);
                    editor.putString("code","en");
                    editor.commit();
                    editor.apply();
                    restartActivity(getActivity());

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUp.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,new AdminLoginFragment()).commit();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,new ForgotPasswordFragment()).commit();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
//        login.setOnClickListener(this :: signinUser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinUser(view);
            }
        });
        return view;
    }

    private void signinUser(View view) {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(getContext(), "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Password)){
            Toast.makeText(getContext(), "Password Cannot be Empty", Toast.LENGTH_SHORT).show();
        }
        else{


            firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(UserType, "user");
                        editor.commit();
                        Intent intent = new Intent(getActivity(),UserHome.class);
                        startActivity(intent);
                        Toast.makeText(getContext(),"User Logged In SuccessFully",Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(getContext(),"Wrong Password or Email",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    public void ChangeLanguage(String language){

        sharedPreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
    }

    public void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }
}