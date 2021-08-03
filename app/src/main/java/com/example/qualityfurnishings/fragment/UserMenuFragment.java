package com.example.qualityfurnishings.fragment;

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

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.FavouriteProducts;
import com.example.qualityfurnishings.activity.LoginActivity;
import com.example.qualityfurnishings.activity.UserMyOrders;

import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.qualityfurnishings.SplashScreen.UserIdPref;
import static com.example.qualityfurnishings.activity.UserHome.Userid;


public class UserMenuFragment extends Fragment {
    LinearLayout MyOrders,LogOut,changeLanguage,FavouriteProducts;
    public static final String MyPREFERENCES = "LoginPref" ;
    public static final String UserType = "usertype";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;



    public UserMenuFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getContext().getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        String lang = sharedpreferences.getString("code","en");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        //ChangeLanguage(lang);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_menu, container, false);
        MyOrders = (LinearLayout)view.findViewById(R.id.myorders);
        LogOut = (LinearLayout)view.findViewById(R.id.logout);
        changeLanguage=(LinearLayout)view.findViewById(R.id.changeLanguage);
        FavouriteProducts=(LinearLayout)view.findViewById(R.id.favProducts);
        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserMyOrders.class);
                startActivity(intent);
            }
        });
        FavouriteProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavouriteProducts.class);
                startActivity(intent);
            }
        });
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());
                builder.setMessage("Are you sure you want to Change Language?");

                // Set Alert Title
                builder.setTitle("Alert !");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }
}