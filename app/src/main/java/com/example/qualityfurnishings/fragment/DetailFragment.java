package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.ProductModal;


public class DetailFragment extends Fragment {
    ImageView productimage;
    TextView productname,price,description,quantity;
    Button cart;
    ProductModal modal;




    public DetailFragment(ProductModal productModal) {
        modal=productModal;
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        productimage=(ImageView)view.findViewById(R.id.ProductImage);
        productname =(TextView)view.findViewById(R.id.tvProductName);
        price=(TextView)view.findViewById(R.id.tvPrice);
        description=(TextView)view.findViewById(R.id.tvDescription);
        quantity=(TextView)view.findViewById(R.id.tvitemcount);
        cart=(Button)view.findViewById(R.id.btAddtocart);
        Glide.with(getContext())
                .load(modal.getImage())
                .into(productimage);
        productname.setText(modal.getName());
        int iPrice = modal.getPrice();
        String stPrice = Integer.toString(iPrice);
        price.setText(stPrice);
        description.setText(modal.getDescription());

        int itemcount = modal.getQuantity();
        String stQuantity = Integer.toString(itemcount);
        quantity.setText(stPrice);


        return view;
    }
}