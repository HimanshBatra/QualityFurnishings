package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;


public class DetailFragment extends Fragment {
    ImageView productimage;
    TextView productname,price,description,quantity,discount,dollar,originalDollar;
    Button cart;
    ProductModal modal;
    LinearLayout OriginalPriceview,DiscountPriceview;
    String category,subcategory;
    String stdiscount;
    int Cartquantity,finalPrice,discountamount;
    String id;
    FirebaseAuth mAuth;
    String user;
    String s1;





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
        discount=(TextView)view.findViewById(R.id.tvDiscount);
        dollar=(TextView)view.findViewById(R.id.discountdollar);
        originalDollar=(TextView)view.findViewById(R.id.originaldollar);
        OriginalPriceview=(LinearLayout)view.findViewById(R.id.originalpriceview);
        DiscountPriceview=(LinearLayout)view.findViewById(R.id.discountview);

        cart=(Button)view.findViewById(R.id.btAddtocart);
        DiscountPriceview.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPref", MODE_PRIVATE);
         s1 = sharedPreferences.getString("userid","");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();


        Glide.with(getContext())
                .load(modal.getImage())
                .into(productimage);
        productname.setText(modal.getName());
        String ProductName=modal.getName();
        String image=modal.getImage();
        category=modal.getCategory();
        subcategory=modal.getSubCategory();

        int iPrice = modal.getPrice();
        String stPrice = Integer.toString(iPrice);
        price.setText(stPrice);
        description.setText(modal.getDescription());

        int itemcount = modal.getQuantity();
        String stQuantity = Integer.toString(itemcount);
        quantity.setText(stQuantity);

        int saleprice=modal.getDiscount();
        if (!(saleprice ==0)) {
            DiscountPriceview.setVisibility(View.VISIBLE);
            discountamount = iPrice-((iPrice *saleprice)/100);
            stdiscount= Integer.toString(discountamount);
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            originalDollar.setPaintFlags(originalDollar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            discount.setText(stdiscount);
        }
        if (saleprice==0){
            finalPrice=iPrice;
        }
        else{
            finalPrice=discountamount;
        }

        Cartquantity= 1;



        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference db_ref = database.child("users").child(user).child("cart").push();
                Cart cart =new Cart(ProductName,image,category,subcategory,Cartquantity,finalPrice,db_ref.getKey(),s1,finalPrice);
                db_ref.setValue(cart);
                Toast.makeText(getContext(), "Product Successfully Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}