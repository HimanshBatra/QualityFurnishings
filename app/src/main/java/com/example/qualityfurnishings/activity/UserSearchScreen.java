package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.UserSearchAdapter;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserSearchScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    String Category;
    ImageView Imgsearch,Filter;
    TextView searchProduct;
    String flag="false";
    List<ProductModal> productList;
    ArrayList<ProductModal> searchProductList;
    UserSearchAdapter userSearchAdapter;
    public static Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getApplicationContext().getSharedPreferences("LANGUAGE",getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_screen);
        context = getApplicationContext();
        recyclerView = findViewById(R.id.productsView);
        Imgsearch=(ImageView)findViewById(R.id.search);
        Filter=(ImageView)findViewById(R.id.imgFilter);
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("category",Category);
                startActivity(intent);
            }
        });
        searchProduct=findViewById(R.id.etSearch);
        searchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("category",Category);
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Category=b.getString("category");
        loadProducts();
        Imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchvalue=searchProduct.getText().toString();
                flag="false";
                if(!TextUtils.isEmpty(searchvalue)){
                    searchProductList=new ArrayList<>();
                    for(int i=0;i<productList.size();i++){
                        if (productList.get(i).getName().toLowerCase().contains(searchvalue.trim().toLowerCase())){
                            flag="true";
                            searchProductList.add(new ProductModal(productList.get(i).getImage(), productList.get(i).getName(),productList.get(i).getDescription(),
                                    productList.get(i).getQuality(),productList.get(i).getQuantity(),productList.get(i).getPrice(),productList.get(i).isSale(),
                                    productList.get(i).getDiscount(), productList.get(i).getId(),Category,productList.get(i).getSubCategory()));
                            userSearchAdapter = new UserSearchAdapter(searchProductList, UserSearchScreen.this);
                            recyclerView.setAdapter(userSearchAdapter);


                        }

                    }

                    if(flag.equals("false")){
                        searchProductList.clear();
                        userSearchAdapter = new UserSearchAdapter(searchProductList, UserSearchScreen.this);
                        recyclerView.setAdapter(userSearchAdapter);

                    }

                }
                else{
                    loadProducts();
                }
            }
        });

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