package com.example.qualityfurnishings.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.UserHome;
import com.example.qualityfurnishings.model.AdminModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static android.content.ContentValues.TAG;


public class AdminLoginFragment extends Fragment {
    TextView adminlanguage;
    EditText email,password;
    Button login;
    TextView user,admin;
//    private FirebaseAuth firebaseAuth;
    public static final String MyPREFERENCES = "LoginPref" ;
    public static final String UserType = "usertype";
    SharedPreferences sharedpreferences;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    String english = "English";
    String french = "French";


    public AdminLoginFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_admin_login, container, false);
        email=view.findViewById(R.id.etEmail);
        password=view.findViewById(R.id.etPssword);
        login=view.findViewById(R.id.btAdminLogin);
        login.setBackgroundColor(Color.parseColor("#1F2633"));
        user = view.findViewById(R.id.tvUser);
        admin = view.findViewById(R.id.tvAdmin);

        adminlanguage = view.findViewById(R.id.textViewLang);

// For text change on login screen
        String lang = sharedPreferences.getString("code","en");
        if(lang.equals("en")){
            adminlanguage.setText(french);
        }else if(lang.equals("fr")){
            adminlanguage.setText(english);
        }

// For on Click function language change
        adminlanguage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String screenLanguage = adminlanguage.getText().toString();
                if(screenLanguage.equals("French")){

                    adminlanguage.setText(english);
                    Log.d(TAG, "onClick:lang");
                    editor.putString("code","fr");
                    editor.commit();
                    editor.apply();
                    restartActivity(getActivity());


                }
                if(screenLanguage.equals("English")){
                    adminlanguage.setText(french);
                    editor.putString("code","en");
                    editor.commit();
                    editor.apply();
                    restartActivity(getActivity());

                }
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
//        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                String stEmail = email.getText().toString().trim();
                String stPassword = password.getText().toString().trim();

//
//               AdminModal user = new AdminModal("admin", "123","admin@gmail.com");
//                DatabaseReference db_ref = database.child("FurnitureCategory").child("Admin").push();
//                db_ref.setValue(user);
//                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                database.child("FurnitureCategory").child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {





                            AdminModal admin = snapshot.getValue(AdminModal.class);
                            String FrMail=admin.getEmail();
                            String FrPassword=admin.getPassword();
//                            System.out.println(FrPassword);
//                            System.out.println(FrMail);

                            if((!stEmail.equals(FrMail)) || !stPassword.equals(FrPassword)){
                                Toast.makeText(getContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                            }
//                            if((!stPassword.equals(FrPassword))){
//                                Toast.makeText(getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
//                            }
                            else{
                                sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(UserType, "admin");
                                editor.commit();
                                Intent intent = new Intent(getActivity(),AdminHome.class);
                                startActivity(intent);
                            }








                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        return view;
    }

    public void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }
}