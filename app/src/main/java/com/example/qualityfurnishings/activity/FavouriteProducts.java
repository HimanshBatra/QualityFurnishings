package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.CartAdapter;
import com.example.qualityfurnishings.adapter.FavouriteProductsAdapter;
import com.example.qualityfurnishings.model.Favouties;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouriteProducts extends AppCompatActivity {
    private RecyclerView recyclerView;
    String firebaseUserId;
    private ArrayList<Favouties> favouritiesList;
    FavouriteProductsAdapter favouriteProductsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_favourite_products);
        recyclerView = findViewById(R.id.favproductsView);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        firebaseUserId = sharedPreferences.getString("userid","");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Favourities").child(firebaseUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                favouritiesList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Favouties listData = dataSnapshot.getValue(Favouties.class);
                    favouritiesList.add(new Favouties(listData.getImage(),listData.getName(),listData.getDescription(), listData.getQuality(), listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),listData.getId(),listData.getCategory(),listData.getSubCategory(),listData.getUserid(),listData.isFavourity()));


                }
                favouriteProductsAdapter = new FavouriteProductsAdapter(favouritiesList,getApplicationContext());
                recyclerView.setAdapter(favouriteProductsAdapter);

            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

    }
}