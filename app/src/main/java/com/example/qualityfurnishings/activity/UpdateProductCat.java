package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qualityfurnishings.R;

public class UpdateProductCat extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
    LinearLayout Bed,Dressers,Makeup,Vanity;
    LinearLayout Sofas,Coffee,BookCases,Cabinets;
    LinearLayout KitchenIslands,BakersRacks,FoodPanitries,WineRacks;
    LinearLayout BathroomVanities,BathroomCabinets,Medicine;
    LinearLayout OfficeChairs,PrinterStands,OfficeStools,Laptop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_cat);
//        getSupportFragmentManager().beginTransaction().replace(R.id.categorycontainer, new AdminCategorySelect()).commit();
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
//                getSupportFragmentManager().beginTransaction().replace(R.id.categorycontainer,temp).commit();
//                return true;
//            }
//        });


        Bed=(LinearLayout)findViewById(R.id.ctbed);
        Dressers=(LinearLayout)findViewById(R.id.ctdressers);
        Makeup=(LinearLayout)findViewById(R.id.ctmakeup);
        Vanity=(LinearLayout)findViewById(R.id.ctvanity);
        //Living Room
        Sofas = (LinearLayout)findViewById(R.id.ctsofa);
        Coffee = (LinearLayout)findViewById(R.id.ctcoeffetables);
        BookCases = (LinearLayout)findViewById(R.id.ctbookcases);
        Cabinets = (LinearLayout)findViewById(R.id.ctcabinets);
        //Kitchen
        KitchenIslands = (LinearLayout)findViewById(R.id.ctislands);
        BakersRacks = (LinearLayout)findViewById(R.id.ctbakersracks);
        FoodPanitries = (LinearLayout)findViewById(R.id.ctfoodpanitries);
        WineRacks = (LinearLayout)findViewById(R.id.ctwineracks);
        //Bathroom
        BathroomVanities= (LinearLayout)findViewById(R.id.ctbathroomvanities);
        BathroomCabinets= (LinearLayout)findViewById(R.id.ctbathroomcabinets);
        Medicine= (LinearLayout)findViewById(R.id.ctmedicine);
        //Office
        PrinterStands=(LinearLayout)findViewById(R.id.ctprinterstands);
        OfficeStools=(LinearLayout)findViewById(R.id.ctofficestools);
        OfficeChairs=(LinearLayout)findViewById(R.id.ctofficechairs);
        Laptop=(LinearLayout)findViewById(R.id.ctlaptopcart);

        // BEDROOM ON CLICK LISTENER
        Bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("BedRoom","Bed");
            }
        });
        Dressers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("BedRoom","Dressers");
            }
        });
        Makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("BedRoom","Makeup Vanities");
            }
        });
        Vanity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("BedRoom","Vanity Stool");
            }
        });
//Living Room on click listener
        Sofas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("LivingRoom","Sofas");
            }
        });

        Coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("LivingRoom","Coffee Tables");
            }
        });
        BookCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("LivingRoom","Book Cases");
            }
        });

        Cabinets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("LivingRoom","Cabinets & Chests");
            }
        });
        //kitchen on click listener
        KitchenIslands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Kitchen","Kitchen Islands");
            }
        });
        BakersRacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Kitchen","Bakers Racks");
            }
        });
        FoodPanitries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Kitchen","Food Pantries");
            }
        });
        WineRacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Kitchen","Wine Racks");
            }
        });
        //bathroom on click listener
        BathroomVanities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Bathroom","Bathroom Vanities");
            }
        });
        BathroomCabinets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Bathroom","Bathroom Cabinets");
            }
        });
        Medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Bathroom","Medicine Cabinets");
            }
        });
        //office on click listener
        OfficeChairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Office","Office Chairs");
            }
        });
        PrinterStands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Office","Printer Stands");
            }
        });
        OfficeStools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Office","Office Stools");
            }
        });
        Laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData("Office","Laptop Carts & Stands");
            }
        });


    }
    private void showData( String category ,String subCategory){
        Intent intent = new Intent(getApplicationContext(), AdminSubCategorySelection.class);
        intent.putExtra("main",category);
        intent.putExtra("submain",subCategory);
        startActivity(intent);



    }
    }
