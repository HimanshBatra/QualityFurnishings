package com.example.qualityfurnishings.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.AdminSubCatAdapter;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AdminSubCategory extends Fragment {
    private RecyclerView recyclerView;
    List<ProductModal> adminsubcategorylist;
    AdminSubCatAdapter adminSubCatAdapter;
    String categoryName,subcategoryName;

    public AdminSubCategory(String category, String subcategory) {
        categoryName = category;
        subcategoryName=subcategory;

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
       View view = inflater.inflate(R.layout.fragment_admin_sub_category, container, false);
        recyclerView =view.findViewById(R.id.adminsubcategory);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        //receiving bundle from admincategoryselect
//        String categoryName = this.getArguments().getString("main");
//        String subcategoryName = this.getArguments().getString("submain");
        //database refernce
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(categoryName).child(subcategoryName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminsubcategorylist = new ArrayList<>();
                for (DataSnapshot categorylist : snapshot.getChildren()){
                    ProductModal listData = categorylist.getValue(ProductModal.class);
                    adminsubcategorylist.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categorylist.getKey(),categoryName,subcategoryName));

                }
                adminSubCatAdapter = new AdminSubCatAdapter(adminsubcategorylist,getContext());
                recyclerView.setAdapter(adminSubCatAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       return view;
    }
}