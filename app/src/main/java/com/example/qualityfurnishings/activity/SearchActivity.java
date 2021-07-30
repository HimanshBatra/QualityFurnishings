package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.UserSearchAdapter;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String Category;
    ImageView Imgsearch, ImgRefresh;
    TextView searchProduct;
    ChipGroup QualityGroup,PriceGroup;
    String flag="false";
    ArrayList<ProductModal> productList;
    ArrayList<ProductModal> searchProductList;
    ArrayList<ProductModal> qualitySearchList;
    ArrayList<ProductModal> priceSearchList;
    UserSearchAdapter userSearchAdapter;
    String Quality="";
    Button superior, wellfounded,cheap,unwarranted;
    Button priceZero, priceFifty,priceHundred,priceOneFifty;
    int priceMin=0,priceMax=1;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = getApplicationContext();
//        QualityGroup=findViewById(R.id.quality);
//        PriceGroup=findViewById(R.id.GroupPrice);
        recyclerView = findViewById(R.id.productsSerachView);
        superior =(Button) findViewById(R.id.superiorChip);
        wellfounded = (Button) findViewById(R.id.wellChip);
        cheap = (Button) findViewById(R.id.cheapChip);
        unwarranted = (Button) findViewById(R.id.unwarrantedChip);
        Imgsearch=(ImageView)findViewById(R.id.search);
        ImgRefresh=(ImageView)findViewById(R.id.refresh);
        searchProduct=findViewById(R.id.etSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Category=b.getString("category");
        ImgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //QUALITY COLOR CHANGE
                superior.setBackgroundColor(Color.parseColor("#B8B8B8"));
                wellfounded.setBackgroundColor(Color.parseColor("#B8B8B8"));
                cheap.setBackgroundColor(Color.parseColor("#B8B8B8"));
                unwarranted.setBackgroundColor(Color.parseColor("#B8B8B8"));
                //PRICE RANGE COLOR CHANGE
                priceZero.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceHundred.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceOneFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
                Quality="";
                priceMin=0;
                priceMax=1;
                searchProduct.setText(null);
                loadProducts();
            }
        });
        loadProducts();
        superior.setBackgroundColor(Color.parseColor("#B8B8B8"));
        wellfounded.setBackgroundColor(Color.parseColor("#B8B8B8"));
        cheap.setBackgroundColor(Color.parseColor("#B8B8B8"));
        unwarranted.setBackgroundColor(Color.parseColor("#B8B8B8"));
        superior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quality= "Superior";
                superior.setBackgroundColor(Color.parseColor("#1F2633"));
                wellfounded.setBackgroundColor(Color.parseColor("#B8B8B8"));
                cheap.setBackgroundColor(Color.parseColor("#B8B8B8"));
                unwarranted.setBackgroundColor(Color.parseColor("#B8B8B8"));


            }
        });
        wellfounded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quality= "Well FoUnded";
                superior.setBackgroundColor(Color.parseColor("#B8B8B8"));
                wellfounded.setBackgroundColor(Color.parseColor("#1F2633"));
                cheap.setBackgroundColor(Color.parseColor("#B8B8B8"));
                unwarranted.setBackgroundColor(Color.parseColor("#B8B8B8"));

            }
        });
        cheap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quality= "Cheap";
                superior.setBackgroundColor(Color.parseColor("#B8B8B8"));
                wellfounded.setBackgroundColor(Color.parseColor("#B8B8B8"));
                cheap.setBackgroundColor(Color.parseColor("#1F2633"));
                unwarranted.setBackgroundColor(Color.parseColor("#B8B8B8"));

            }
        });
        unwarranted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quality= "UnWarranted";
                superior.setBackgroundColor(Color.parseColor("#B8B8B8"));
                wellfounded.setBackgroundColor(Color.parseColor("#B8B8B8"));
                cheap.setBackgroundColor(Color.parseColor("#B8B8B8"));
                unwarranted.setBackgroundColor(Color.parseColor("#1F2633"));


            }
        });
//                Chip superior= QualityGroup.findViewById(R.id.superiorChip);
//                Chip wellfounded= QualityGroup.findViewById(R.id.wellChip);
//                Chip cheap= QualityGroup.findViewById(R.id.cheapChip);
//                Chip unWarranted= QualityGroup.findViewById(R.id.unwarrantedChip);
//               superior.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                   @Override
//                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                       Quality= "Superior";
//
//                   }
//               });
//                wellfounded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        Quality= "Well Founded";
//
//                    }
//                });
//                cheap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        Quality= "Cheap";
//
//                    }
//                });
//                unWarranted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        Quality= "UnWarranted";
//
//                    }
//                });





//
//                Chip priceZero= PriceGroup.findViewById(R.id.price0);
//                Chip pricefifty= PriceGroup.findViewById(R.id.price51);
//                Chip priceHundred= PriceGroup.findViewById(R.id.price101);
//                Chip priceOnefifty= PriceGroup.findViewById(R.id.price150);
//                priceZero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        priceMin=1;
//                        priceMax=50;
//
//                    }
//                });
//                pricefifty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        priceMin= 51;
//                        priceMax= 100;
//                    }
//                });
//                priceHundred.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        priceMin= 101;
//                        priceMax= 151;
//                    }
//                });
//                priceOnefifty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        priceMin= 151;
//                        priceMax= 10000;
//
//                    }
//                });
        priceZero = (Button)findViewById(R.id.price0);
        priceFifty = (Button)findViewById(R.id.price50);
        priceHundred = (Button)findViewById(R.id.price100);
        priceOneFifty = (Button)findViewById(R.id.price150);

        priceZero.setBackgroundColor(Color.parseColor("#B8B8B8"));
        priceFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
        priceHundred.setBackgroundColor(Color.parseColor("#B8B8B8"));
        priceOneFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));

        priceZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMin=0;
                priceMax=50;
                priceZero.setBackgroundColor(Color.parseColor("#1F2633"));
                priceFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceHundred.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceOneFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));


            }
        });
        priceFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMin=51;
                priceMax=100;
                priceZero.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceFifty.setBackgroundColor(Color.parseColor("#1F2633"));
                priceHundred.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceOneFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));

            }
        });
        priceHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMin=101;
                priceMax=150;
                priceZero.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceHundred.setBackgroundColor(Color.parseColor("#1F2633"));
                priceOneFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));

            }
        });
        priceOneFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMin=151;
                priceMax=10000;
                priceZero.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceFifty.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceHundred.setBackgroundColor(Color.parseColor("#B8B8B8"));
                priceOneFifty.setBackgroundColor(Color.parseColor("#1F2633"));

            }
        });
        Imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueSearch();
//                Log.d("pp", String.valueOf(priceMax));
//                String searchvalue=searchProduct.getText().toString();
//                flag="false";
//                if(!TextUtils.isEmpty(searchvalue) && Quality=="" && priceMax==1){
//                    searchProductList=new ArrayList<>();
//                    for(int i=0;i<productList.size();i++){
//                        if (productList.get(i).getName().toLowerCase().contains(searchvalue.trim().toLowerCase())){
//                            flag="true";
//                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
//                                    productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
//                                    productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//                            userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                            recyclerView.setAdapter(userSearchAdapter);
//
//
//                        }
//
//                    }
//
//                    if(flag.equals("false")){
//                        searchProductList.clear();
//                        userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                        recyclerView.setAdapter(userSearchAdapter);
//
//                    }
//
//                }
//                else if(TextUtils.isEmpty(searchvalue) && Quality!="" && priceMax==1) {
//                    searchProductList = new ArrayList<>();
//                    flag = "false";
//                    for (int i = 0; i < productList.size(); i++) {
//                        if (productList.get(i).getQuality().toLowerCase().contains(Quality.trim().toLowerCase())) {
//                            flag = "true";
//                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(), productList.get(i).getDescription(),
//                                    productList.get(i).getQuality(), productList.get(i).getQuantity(), productList.get(i).getPrice(), productList.get(i).isSale(),
//                                    productList.get(i).getDiscount(), productList.get(i).getId(), Category, productList.get(i).getSubCategory()));
//                            userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                            recyclerView.setAdapter(userSearchAdapter);
//
//
//                        }
//
//                    }
//                    if (flag.equals("false")) {
//                        searchProductList.clear();
//                        userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                        recyclerView.setAdapter(userSearchAdapter);
//
//                    }
//                }
//                else if(TextUtils.isEmpty(searchvalue) && Quality=="" && priceMax!=1){
//                        searchProductList=new ArrayList<>();
//                        flag="false";
//                        for(int i=0;i<productList.size();i++){
//                            int tempPrice = productList.get(i).getPrice();
//                            if(tempPrice>priceMin && tempPrice <priceMax){
//                                flag="true";
//                                searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
//                                        productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
//                                        productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//
//                                userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                                recyclerView.setAdapter(userSearchAdapter);
//
//
//
//                            }
//                        }
//                        if(flag.equals("false")){
//                            searchProductList.clear();
//                            userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                            recyclerView.setAdapter(userSearchAdapter);
//
//                        }
//
//
//
//                }
//                else if(!TextUtils.isEmpty(searchvalue) && Quality!="" && priceMax!=1){
//                    searchProductList=new ArrayList<>();
//                    flag="false";
//                    for(int i=0;i<productList.size();i++){
//                        int tempPrice = productList.get(i).getPrice();
//                        if (productList.get(i).getName().toLowerCase().contains(searchvalue.trim().toLowerCase())
//                                && productList.get(i).getQuality().toLowerCase().contains(Quality.trim().toLowerCase())
//                        && tempPrice>priceMin && tempPrice <priceMax){
//                            flag="true";
//                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
//                                    productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
//                                    productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//
//                            userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                            recyclerView.setAdapter(userSearchAdapter);
//
//
//                        }
//
//                    }
//                    if(flag.equals("false")){
//                        searchProductList.clear();
//                        userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                        recyclerView.setAdapter(userSearchAdapter);
//
//                    }
//                }
//                else if(Quality!=""){
//                    searchProductList=new ArrayList<>();
//                    flag="false";
//                    for(int i=0;i<productList.size();i++){
//                        if (productList.get(i).getQuality().toLowerCase().contains(Quality.trim().toLowerCase())){
//                            flag="true";
//                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
//                                    productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
//                                    productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//                            userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                            recyclerView.setAdapter(userSearchAdapter);
//
//
//                        }
//
//                    }
//                    if(flag.equals("false")){
//                        searchProductList.clear();
//                        userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                        recyclerView.setAdapter(userSearchAdapter);
//
//                    }
//                }
//                else if(priceMax!=1){
//                    searchProductList=new ArrayList<>();
//                    flag="false";
//                    for(int i=0;i<productList.size();i++){
//                        int tempPrice = productList.get(i).getPrice();
//                        if(tempPrice>priceMin && tempPrice <priceMax){
//                            flag="true";
//                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
//                                    productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
//                                    productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//
//                                userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                                recyclerView.setAdapter(userSearchAdapter);
//
//
//
//                        }
//                    }
//                    if(flag.equals("false")){
//                        searchProductList.clear();
//                        userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                        recyclerView.setAdapter(userSearchAdapter);
//
//                    }
//
//                }
//
//                else{
//                    loadProducts();
//                }
            }
        });
    }

    private void ValueSearch() {
        String searchvalue = searchProduct.getText().toString();
        flag="false";
        if(!TextUtils.isEmpty(searchvalue) ){
            searchProductList=new ArrayList<>();
            for(int i=0;i<productList.size();i++){
                if (productList.get(i).getName().toLowerCase().contains(searchvalue.trim().toLowerCase())){
                    flag="true";
                    searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
                            productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
                            productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
//                    userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                    recyclerView.setAdapter(userSearchAdapter);



                }

            }
            quality(searchProductList);


//            if(flag.equals("false")){
//                searchProductList.clear();
//                userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                recyclerView.setAdapter(userSearchAdapter);
//
//            }

        }
        else{
            quality(productList);
//            loadProducts();
        }

    }

    private void quality(ArrayList<ProductModal> searchProductList) {
        qualitySearchList=new ArrayList<>();
        flag = "false";
        if(Quality!="") {
            for (int i = 0; i < searchProductList.size(); i++) {
                if (searchProductList.get(i).getQuality().toLowerCase().contains(Quality.trim().toLowerCase())) {
                    flag = "true";
                    qualitySearchList.add(new ProductModal(searchProductList.get(i).getImage(), searchProductList.get(i).getName(), searchProductList.get(i).getDescription(),
                            searchProductList.get(i).getQuality(), searchProductList.get(i).getQuantity(), searchProductList.get(i).getPrice(), searchProductList.get(i).isSale(),
                            searchProductList.get(i).getDiscount(), searchProductList.get(i).getId(), Category, searchProductList.get(i).getSubCategory()));




                }

            }
            price(qualitySearchList);

//            if (flag.equals("false")) {
//                searchProductList.clear();
//                userSearchAdapter = new UserSearchAdapter(searchProductList, SearchActivity.this);
//                recyclerView.setAdapter(userSearchAdapter);
//
//            }
        }
        else{
            price(searchProductList);
//            loadProducts();
        }

    }

    private void price(ArrayList<ProductModal> qualitySearchList) {
        priceSearchList=new ArrayList<>();
        flag="false";
        if(priceMax!=1) {
            for (int i = 0; i < qualitySearchList.size(); i++) {
                int tempPrice = qualitySearchList.get(i).getPrice();
                if (tempPrice > priceMin && tempPrice < priceMax) {
                    flag = "true";
                    priceSearchList.add(new ProductModal(qualitySearchList.get(i).getImage(), qualitySearchList.get(i).getName(), qualitySearchList.get(i).getDescription(),
                            qualitySearchList.get(i).getQuality(), qualitySearchList.get(i).getQuantity(), qualitySearchList.get(i).getPrice(), qualitySearchList.get(i).isSale(),
                            qualitySearchList.get(i).getDiscount(), qualitySearchList.get(i).getId(), Category, qualitySearchList.get(i).getSubCategory()));

                    userSearchAdapter = new UserSearchAdapter(priceSearchList, SearchActivity.this);
                    recyclerView.setAdapter(userSearchAdapter);


                }
            }
            if (flag.equals("false")) {
                priceSearchList.clear();
                userSearchAdapter = new UserSearchAdapter(priceSearchList, SearchActivity.this);
                recyclerView.setAdapter(userSearchAdapter);

            }
        }
        else{
            userSearchAdapter = new UserSearchAdapter(qualitySearchList, SearchActivity.this);
            recyclerView.setAdapter(userSearchAdapter);
        }

    }

    private void loadProducts() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(Category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                productList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for (DataSnapshot itemList : dataSnapshot.getChildren()){
                        ProductModal listData = itemList.getValue(ProductModal.class);
                        productList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),
                                listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),
                                listData.getDiscount(),itemList.getKey(),Category,dataSnapshot.getKey()));

                    }
                }
                userSearchAdapter = new UserSearchAdapter(productList,getApplicationContext());
                recyclerView.setAdapter(userSearchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}