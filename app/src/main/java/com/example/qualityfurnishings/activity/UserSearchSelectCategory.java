package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qualityfurnishings.R;

public class UserSearchSelectCategory extends AppCompatActivity {
    Button bedroom,bathroom,livingroom,kitchen,office;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_select_category);
        bedroom=(Button)findViewById(R.id.btBedroom);
        bathroom=(Button)findViewById(R.id.btBathroom);
        livingroom=(Button)findViewById(R.id.btLivingRoom);
        kitchen=(Button)findViewById(R.id.btKitchen);
        office=(Button)findViewById(R.id.btOffice);
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSearchScreen.class);
                intent.putExtra("category","BedRoom");
                startActivity(intent);

            }
        });
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSearchScreen.class);
                intent.putExtra("category","BathRoom");
                startActivity(intent);

            }
        });
        livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSearchScreen.class);
                intent.putExtra("category","LivingRoom");
                startActivity(intent);

            }
        });
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSearchScreen.class);
                intent.putExtra("category","Kitchen");
                startActivity(intent);

            }
        });
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSearchScreen.class);
                intent.putExtra("category","Office");
                startActivity(intent);

            }
        });


    }
}