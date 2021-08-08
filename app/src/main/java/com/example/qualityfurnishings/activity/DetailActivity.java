package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
//    BottomNavigationView usernavigation;
    ImageView productimage,SaveProduct;
    TextView productname,price,description,quantity,discount,dollar,originalDollar,quality;
    Button cart;


    LinearLayout OriginalPriceview,DiscountPriceview;
    String category,subcategory;
    String stdiscount;
    String stDescription,stQuality;
    int Cartquantity,finalPrice,discountamount;
    String id;
    FirebaseAuth mAuth;
    String user;
    String s1;
    String ProductName;
    String image;
    int itemcount;
    boolean favourity;
    String dbKey;
    int iPrice;
    boolean blSale;
    int saleprice;
    SharedPreferences sharedPreferences;
    String pcCart;
    String pcaddedFav;
    String pcRemovedFav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        pcCart= getResources().getString(R.string.pac);
        pcaddedFav= getResources().getString(R.string.prf);
        pcRemovedFav=getResources().getString(R.string.psf);

        setContentView(R.layout.activity_detail);
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
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String data = b.getString("main");
        if(data.equals("main")){
            ProductModal modal;
            modal = intent.getParcelableExtra("productdata");
            Log.d("data", String.valueOf(modal));
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
            stQuality=modal.getQuality();
            blSale=modal.isSale();
            iPrice = modal.getPrice();
            String stPrice = Integer.toString(iPrice);
            price.setText(stPrice);
            description.setText(modal.getDescription());
            stDescription=modal.getDescription();

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

            saleprice=modal.getDiscount();
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
            favourity = false;
            SaveProduct.setBackgroundResource(R.drawable.whiteheart);

        }
        else{
            Favouties modal;
            modal = intent.getParcelableExtra("productdata");
            Log.d("data", String.valueOf(modal));
            modal = intent.getParcelableExtra("productdata");
            Log.d("data", String.valueOf(modal));
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
            stQuality=modal.getQuality();
            blSale=modal.isSale();
            iPrice = modal.getPrice();
            String stPrice = Integer.toString(iPrice);
            price.setText(stPrice);
            description.setText(modal.getDescription());
            stDescription=modal.getDescription();

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

            saleprice=modal.getDiscount();
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
            favourity = false;
            SaveProduct.setBackgroundResource(R.drawable.whiteheart);

        }







        SaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favourity == true){
                    removefavourity();
                }
                else {
                    addfavourity();
                }

            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Favourities").child(s1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    Favouties listData = dataSnapshot.getValue(Favouties.class);
                    if(listData.getName().equals(ProductName)){
                        if(listData.isFavourity()==true){
                            favourity= true;
                            dbKey=listData.getId();
                            SaveProduct.setBackgroundResource(R.drawable.like);
                        }
                        else {
                            favourity = false;
                            SaveProduct.setBackgroundResource(R.drawable.whiteheart);
                        }
                    }



                }

            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference db_ref = database.child("FurnitureCategory").child("Cart").child(s1).push();
                Cart cart =new Cart(ProductName,image,category,subcategory,Cartquantity,finalPrice,db_ref.getKey(),s1,finalPrice,itemcount);
                db_ref.setValue(cart);
                Toast.makeText(getApplicationContext(), pcCart, Toast.LENGTH_SHORT).show();

            }
        });

    }



    private void removefavourity() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Favourities").child(s1).child(dbKey).removeValue();
        favourity = false;
        SaveProduct.setBackgroundResource(R.drawable.whiteheart);
        Toast.makeText(getApplicationContext(), pcRemovedFav, Toast.LENGTH_SHORT).show();

    }

    private void addfavourity() {
        favourity = true;
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = database1.child("FurnitureCategory").child("Favourities").child(s1).push();
        databaseReference.getKey();
        Favouties favouties =new Favouties(image,ProductName,stDescription,stQuality,itemcount,iPrice,blSale,saleprice,databaseReference.getKey(),category,subcategory,s1,favourity);
        databaseReference.setValue(favouties);
        SaveProduct.setBackgroundResource(R.drawable.like);
        Toast.makeText(getApplicationContext(),pcaddedFav, Toast.LENGTH_SHORT).show();

    }

}