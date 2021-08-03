package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.Favouties;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
//    BottomNavigationView usernavigation;
    ImageView productimage,SaveProduct;
    TextView productname,price,description,quantity,discount,dollar,originalDollar,quality;
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
    String ProductName;
    String image;
    int itemcount;
    boolean favourity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        ProductModal modal = intent.getParcelableExtra("productdata");



        productimage=(ImageView)findViewById(R.id.ProductImage);
        productname =(TextView)findViewById(R.id.tvProductName);
        price=(TextView)findViewById(R.id.tvPrice);
        description=(TextView)findViewById(R.id.tvDescription);
        quantity=(TextView)findViewById(R.id.tvitemcount);
        discount=(TextView)findViewById(R.id.tvDiscount);
        dollar=(TextView)findViewById(R.id.discountdollar);
        originalDollar=(TextView)findViewById(R.id.originaldollar);
        quality=(TextView)findViewById(R.id.tvQuality);
        OriginalPriceview=(LinearLayout)findViewById(R.id.originalpriceview);
        DiscountPriceview=(LinearLayout)findViewById(R.id.discountview);
        SaveProduct=(ImageView) findViewById(R.id.btLike);

        cart=(Button)findViewById(R.id.btAddtocart);
        DiscountPriceview.setVisibility(View.GONE);
        SaveProduct.setBackgroundResource(R.drawable.whiteheart);
        SaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfavourity();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        s1 = sharedPreferences.getString("userid","");
        Glide.with(getApplicationContext())
                .load(modal.getImage())
                .into(productimage);
        productname.setText(modal.getName());

        ProductName=modal.getName();
        image=modal.getImage();
        category=modal.getCategory();
        subcategory=modal.getSubCategory();
        quality.setText(modal.getQuality());

        int iPrice = modal.getPrice();
        String stPrice = Integer.toString(iPrice);
        price.setText(stPrice);
        description.setText(modal.getDescription());

        itemcount = modal.getQuantity();
        if(itemcount==0){
            quantity.setText("Out of Stock");
            cart.setVisibility(View.GONE);
        }
        else {
            quantity.setText("Product Available");
        }
//        String stQuantity = Integer.toString(itemcount);
//        quantity.setText(stQuantity);

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
                DatabaseReference db_ref = database.child("FurnitureCategory").child("Cart").child(s1).push();
                Cart cart =new Cart(ProductName,image,category,subcategory,Cartquantity,finalPrice,db_ref.getKey(),s1,finalPrice,itemcount);
                db_ref.setValue(cart);
                Toast.makeText(getApplicationContext(), "Product Successfully Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void addfavourity() {
        favourity = true;
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = database1.child("FurnitureCategory").child("Favourities").child(s1).push();
        Favouties favouties =new Favouties(ProductName,image,category,subcategory,Cartquantity,finalPrice,databaseReference.getKey(),s1,finalPrice,itemcount,favourity);
        databaseReference.setValue(favouties);
        SaveProduct.setBackgroundResource(R.drawable.like);
        Toast.makeText(getApplicationContext(), "Product Successfully Added to Favourities", Toast.LENGTH_SHORT).show();

    }

}