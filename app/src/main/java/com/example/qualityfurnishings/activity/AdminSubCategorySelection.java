package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

public class AdminSubCategorySelection extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
private RecyclerView recyclerView;
    List<ProductModal> adminsubcategorylist;
    AdminSubCatAdapter adminSubCatAdapter;
    String categoryName,subcategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sub_category);
        Intent intent = getIntent();
        categoryName= intent.getStringExtra("main");
        subcategoryName= intent.getStringExtra("submain");

//
//        getSupportFragmentManager().beginTransaction().replace(R.id.listConatiner, new AdminSubCategory(category,subcategory)).commit();
//        bottomNavigationView = (BottomNavigationView)findViewById(R.id.adminbottomnav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment temp=null;
//                switch(item.getItemId())
//                {
//                    case R.id.adminHome : temp = new AdminHomeFragment();
//                        break;
//                    case R.id.adminProfile : temp = new AdminProfileFragment();
//                        break;
//                    case R.id.adminLogout : temp = new AdminLogout();
//
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.listConatiner,temp).commit();
//                return true;
//            }
//        });

        recyclerView =findViewById(R.id.adminsubcategory);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(categoryName).child(subcategoryName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminsubcategorylist = new ArrayList<>();
                for (DataSnapshot categorylist : snapshot.getChildren()){
                    ProductModal listData = categorylist.getValue(ProductModal.class);
                    adminsubcategorylist.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categorylist.getKey(),categoryName,subcategoryName));

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