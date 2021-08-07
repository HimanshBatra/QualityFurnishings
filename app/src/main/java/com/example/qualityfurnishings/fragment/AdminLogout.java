package com.example.qualityfurnishings.fragment;

import static com.example.qualityfurnishings.SplashScreen.UserIdPref;
import static com.example.qualityfurnishings.activity.UserHome.Userid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;


public class AdminLogout extends Fragment {
    LinearLayout changeLanguage,LogOut;
    public static final String MyPREFERENCES = "LoginPref" ;
    public static final String UserType = "usertype";
    SharedPreferences sharedpreferences;
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";


    public AdminLogout() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String screen = "Menu";
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Screen, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString(lastScreen, screen);
        editor1.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_admin_logout, container, false);
        changeLanguage = (LinearLayout)view.findViewById(R.id.adminLanguage);
        LogOut = (LinearLayout)view.findViewById(R.id.adminLogOut);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());
                builder.setMessage("Are you sure you want to LogOut?");

                // Set Alert Title
                builder.setTitle("Alert !");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(UserType, "");
                        editor.commit();
                        //
                        sharedpreferences = getActivity().getSharedPreferences(UserIdPref, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = sharedpreferences.edit();
                        editor2.putString(Userid, "");
                        editor2.commit();
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
//                        getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame,new UserMenuFragment()).commit();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}