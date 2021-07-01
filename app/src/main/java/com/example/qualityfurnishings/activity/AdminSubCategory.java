package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.AdminSubCatAdapter;
import com.example.qualityfurnishings.adapter.subcategory1Adapter;
import com.example.qualityfurnishings.model.SubCatrgory1Modal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AdminSubCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<SubCatrgory1Modal> adminsubcategorylist;
    AdminSubCatAdapter adminSubCatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sub_category);
        recyclerView =findViewById(R.id.adminsubcategory);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        String categoryName = extras.getString("main");
        String subcategoryName = extras.getString("submain");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(categoryName).child(subcategoryName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminsubcategorylist = new ArrayList<>();
                for (DataSnapshot categorylist : snapshot.getChildren()){
                    SubCatrgory1Modal listData = categorylist.getValue(SubCatrgory1Modal.class);
                    adminsubcategorylist.add(new SubCatrgory1Modal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categorylist.getKey(),categoryName,subcategoryName));

                }
                adminSubCatAdapter = new AdminSubCatAdapter(adminsubcategorylist,getApplicationContext());
                recyclerView.setAdapter(adminSubCatAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}