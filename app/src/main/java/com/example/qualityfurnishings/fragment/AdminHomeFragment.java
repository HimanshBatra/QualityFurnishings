package com.example.qualityfurnishings.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qualityfurnishings.activity.AdminAddProducts;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminOrderList;
import com.example.qualityfurnishings.activity.UpdateProductCat;

import java.util.Locale;


public class AdminHomeFragment extends Fragment {
    LinearLayout addProducts,updateProduct,orderList;
    String english = "English";
    String french = "French";
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";



    public AdminHomeFragment() {


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        String screen = "Home";
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Screen, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString(lastScreen, screen);
        editor1.commit();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        addProducts = (LinearLayout)view.findViewById(R.id.AddProducts);
        updateProduct = (LinearLayout)view.findViewById(R.id.UpdateProducts);
        orderList = (LinearLayout)view.findViewById(R.id.OrdersList);
        addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AdminAddProducts.class);
                getActivity().startActivity(i);
            }
        });
        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProductCat.class);
                startActivity(intent);
            }
        });
        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminOrderList.class);
                startActivity(intent);
            }
        });


        return  view;

    }
}