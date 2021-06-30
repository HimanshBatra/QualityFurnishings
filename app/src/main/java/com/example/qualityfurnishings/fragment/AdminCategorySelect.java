package com.example.qualityfurnishings.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qualityfurnishings.R;


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
        return view;
    }
}