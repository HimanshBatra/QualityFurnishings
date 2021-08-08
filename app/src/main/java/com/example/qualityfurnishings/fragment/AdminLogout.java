package com.example.qualityfurnishings.fragment;

import static android.content.ContentValues.TAG;
import static com.example.qualityfurnishings.SplashScreen.UserIdPref;
import static com.example.qualityfurnishings.activity.UserHome.Userid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserHome;

import java.util.Locale;


public class AdminLogout extends Fragment {
    LinearLayout changeLanguage,LogOut;
    public static final String MyPREFERENCES = "LoginPref" ;
    public static final String UserType = "usertype";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";
    String data;
    String c;
    String y;
    String a;
    String alertLanguage;


    public AdminLogout() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        //editor = sharedpreferences.edit();
        String lang = sharedpreferences.getString("code","en");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        //ChangeLanguage(lang);
        String screen = "Menu";
        data = getResources().getString(R.string.alertDialogbox);
        c=getResources().getString(R.string.cancel);
        y=getResources().getString(R.string.yes);
        a=getResources().getString(R.string.alert);
        alertLanguage=getResources().getString(R.string.alertLanguagebox);

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

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());
                builder.setMessage(alertLanguage);

                // Set Alert Title
                builder.setTitle(a);
                builder.setPositiveButton(y, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedpreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
                        editor = sharedpreferences.edit();
                        String lang = sharedpreferences.getString("code","en");

                        if(lang.equals("fr")){

                            //language.setText(english);
                            Log.d(TAG, "onClick:lang");
                            editor.putString("code","en");
                            editor.commit();
                            editor.apply();
                            restartActivity(getActivity());


                        }
                        if(lang.equals("en")){
                            // language.setText(french);
                            editor.putString("code","fr");
                            editor.commit();
                            editor.apply();
                            restartActivity(getActivity());

                        }

                    }
                });
                builder.setNegativeButton(c, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
//                        getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame,new UserMenuFragment()).commit();
                    }
                });
                builder.show();
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());
                builder.setMessage(data);

                // Set Alert Title
                builder.setTitle(a);
                builder.setPositiveButton(y, new DialogInterface.OnClickListener() {
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
                builder.setNegativeButton(c, new DialogInterface.OnClickListener() {
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
    public void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }
}