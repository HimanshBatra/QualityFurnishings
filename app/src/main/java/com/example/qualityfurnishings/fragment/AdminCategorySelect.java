package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminSubCategorySelection;


public class AdminCategorySelect extends Fragment {
    LinearLayout Bed,Dressers,Makeup,Vanity;
    LinearLayout Sofas,Coffee,BookCases,Cabinets;
    LinearLayout KitchenIslands,BakersRacks,FoodPanitries,WineRacks;
    LinearLayout BathroomVanities,BathroomCabinets,Medicine;
    LinearLayout OfficeChairs,PrinterStands,OfficeStools,Laptop;





    public AdminCategorySelect() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_admin_category_select, container, false);
        //Bedroom
        Bed=(LinearLayout)view.findViewById(R.id.ctbed);
        Dressers=(LinearLayout)view.findViewById(R.id.ctdressers);
        Makeup=(LinearLayout)view.findViewById(R.id.ctmakeup);
        Vanity=(LinearLayout)view.findViewById(R.id.ctvanity);
        //Living Room
        Sofas = (LinearLayout)view.findViewById(R.id.ctsofa);
        Coffee = (LinearLayout)view.findViewById(R.id.ctcoeffetables);
        BookCases = (LinearLayout)view.findViewById(R.id.ctbookcases);
        Cabinets = (LinearLayout)view.findViewById(R.id.ctcabinets);
        //Kitchen
        KitchenIslands = (LinearLayout)view.findViewById(R.id.ctislands);
        BakersRacks = (LinearLayout)view.findViewById(R.id.ctbakersracks);
        FoodPanitries = (LinearLayout)view.findViewById(R.id.ctfoodpanitries);
        WineRacks = (LinearLayout)view.findViewById(R.id.ctwineracks);
        //Bathroom
        BathroomVanities= (LinearLayout)view.findViewById(R.id.ctbathroomvanities);
        BathroomCabinets= (LinearLayout)view.findViewById(R.id.ctbathroomcabinets);
        Medicine= (LinearLayout)view.findViewById(R.id.ctmedicine);
        //Office
        PrinterStands=(LinearLayout)view.findViewById(R.id.ctprinterstands);
        OfficeStools=(LinearLayout)view.findViewById(R.id.ctofficestools);
        OfficeChairs=(LinearLayout)view.findViewById(R.id.ctofficechairs);
        Laptop=(LinearLayout)view.findViewById(R.id.ctlaptopcart);
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

        return view;
    }
    private void showData( String category ,String subCategory){
        Intent intent = new Intent(getContext(), AdminSubCategorySelection.class);
        intent.putExtra("main",category);
        intent.putExtra("submain",subCategory);
        startActivity(intent);



    }
}