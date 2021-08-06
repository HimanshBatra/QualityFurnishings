package com.example.qualityfurnishings.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;


public class AdminLogout extends Fragment {
    TextView Cancel,Yes;
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
        Cancel=(TextView)view.findViewById(R.id.tvCancel);
        Yes=(TextView)view.findViewById(R.id.tvYes);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(UserType, "");
                editor.commit();
                startActivity(intent);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity instanceof AdminHome) {
                    ((AdminHome) activity).showAdminHomeFragment();
                }
//                getFragmentManager().beginTransaction().replace(R.id.AdminHomeFrame,new AdminLoginFragment()).commit();
            }
        });
        return view;
    }
}