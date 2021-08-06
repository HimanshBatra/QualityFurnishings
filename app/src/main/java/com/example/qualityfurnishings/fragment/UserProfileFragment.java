package com.example.qualityfurnishings.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.UserEditProfile;
import com.example.qualityfurnishings.activity.UserUpdatePassword;
import com.example.qualityfurnishings.model.UserTestModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class UserProfileFragment extends Fragment {
    TextView username,useremail,userphonenumber,userpassword,useraddress,userpostalcode,userprovince;
    Button Editprofile,ChangePassword;
    String stname,stemail,stpassword,stpostalcode,staddress,stprovince,stphonenumber;


    String english = "English";
    String french = "French";
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";



    public UserProfileFragment() {
        // Required empty public constructor

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        ChangeLanguage(lang);
        String screen = "Profile";
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Screen, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString(lastScreen, screen);
        editor1.commit();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPref", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("userid","");
//        Log.d("userid", s1);

        username=(TextView)view.findViewById(R.id.tvuserName);
        useremail=(TextView)view.findViewById(R.id.tvuserEmail);
        userphonenumber=(TextView)view.findViewById(R.id.tvuserPhoneNumber);
        userpassword=(TextView)view.findViewById(R.id.tvuserPassword);
        useraddress=(TextView)view.findViewById(R.id.tvuserAddress);
        userpostalcode=(TextView)view.findViewById(R.id.tvuserPostalCode);
        userprovince=(TextView)view.findViewById(R.id.tvuserProvince);
        ChangePassword=(Button) view.findViewById(R.id.tvUpdatePassword);
        Editprofile=(Button)view.findViewById(R.id.btuserEditProfile);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("users").child(s1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserTestModal usermodel = snapshot.getValue(UserTestModal.class);
                stname=usermodel.getFullName();
                stemail=usermodel.getEmail();
                staddress=usermodel.getAddress();
                stpassword=usermodel.getPassword();
                stpostalcode=usermodel.getPostalcode();
                stprovince=usermodel.getProvince();
                stphonenumber=usermodel.getPhoneNumber();

                username.setText(stname);
                useremail.setText(stemail);
                userphonenumber.setText(stphonenumber);
                userpassword.setText(stpassword);
                if(stpostalcode.equals("")){
                    userpostalcode.setText("Enter postal code");
                }
                else{
                    userpostalcode.setText(stpostalcode);
                }
                if(staddress.equals("")){
                    useraddress.setText("Please enter your address");
                }
                else{
                    useraddress.setText(staddress);
                }
                if(stprovince.equals("")){
                    userprovince.setText("please enter your province");
                }
                else{
                    userprovince.setText(stprovince);
                }
                Editprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getContext(), UserEditProfile.class);
                        intent.putExtra("userdata",usermodel);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserUpdatePassword.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void ChangeLanguage(String language){

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
    }
}