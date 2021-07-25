package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.qualityfurnishings.R;

public class UserSearchScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    String Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_screen);
        recyclerView = findViewById(R.id.productsView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Category=b.getString("category");
        
    }
}