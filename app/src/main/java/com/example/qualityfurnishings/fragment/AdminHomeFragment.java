package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qualityfurnishings.activity.AdminAddProducts;
import com.example.qualityfurnishings.R;


public class AdminHomeFragment extends Fragment {
    LinearLayout addProducts,updateProduct,orderList;



    public AdminHomeFragment() {


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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
        return  view;

    }
}