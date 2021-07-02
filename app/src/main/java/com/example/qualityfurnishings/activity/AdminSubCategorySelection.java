package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.AdminSubCatAdapter;
import com.example.qualityfurnishings.fragment.AdminCategorySelect;
import com.example.qualityfurnishings.fragment.AdminHomeFragment;
import com.example.qualityfurnishings.fragment.AdminLogout;
import com.example.qualityfurnishings.fragment.AdminProfileFragment;
import com.example.qualityfurnishings.fragment.AdminSubCategory;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminSubCategorySelection extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sub_category);
        Intent intent = getIntent();
        String category = intent.getStringExtra("main");
        String subcategory = intent.getStringExtra("submain");

        getSupportFragmentManager().beginTransaction().replace(R.id.listConatiner, new AdminSubCategory(category,subcategory)).commit();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.adminbottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;
                switch(item.getItemId())
                {
                    case R.id.adminHome : temp = new AdminHomeFragment();
                        break;
                    case R.id.adminProfile : temp = new AdminProfileFragment();
                        break;
                    case R.id.adminLogout : temp = new AdminLogout();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.listConatiner,temp).commit();
                return true;
            }
        });


    }
}