package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

public class UserSearchScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    String Category;
    List<ProductModal> productList;
    UserSearchAdapter userSearchAdapter;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_screen);
        context = getApplicationContext();
        recyclerView = findViewById(R.id.productsView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Category=b.getString("category");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(Category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                productList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for (DataSnapshot itemList : dataSnapshot.getChildren()){
                        ProductModal listData = itemList.getValue(ProductModal.class);
                        productList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),itemList.getKey(),Category,dataSnapshot.getKey()));

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