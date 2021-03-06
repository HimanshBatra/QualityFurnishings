package com.example.qualityfurnishings.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.UserSearchSelectCategory;
import com.example.qualityfurnishings.adapter.salecategoryAdapter;
import com.example.qualityfurnishings.adapter.subcategory2Adapter;
import com.example.qualityfurnishings.adapter.subcategory3Adapter;
import com.example.qualityfurnishings.adapter.subcategory4Adapter;
import com.example.qualityfurnishings.model.ProductModal;
import com.example.qualityfurnishings.adapter.subcategory1Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class UserHomeFragment extends Fragment {

    private RecyclerView recyclerView,test,recyclerview3,recyclerview4,recyclerView5;
    LinearLayout bedroom,livingroom,bathroom,kitchen,office,SearchProduct;
    List<ProductModal> firstCateogyList,secondCategoryList,thirdCategoryList,fourthCategoryList,salecategoryList;
    subcategory1Adapter firstCategoryAdapter;
    TextView firstCategory,secondCategory,thirdCategory,fourthCategory,saleCategory;
    subcategory2Adapter secondCategoryAdapter;
    subcategory3Adapter thirdCategoryAdapter;
    subcategory4Adapter fourrthCategoryAdapter;
    salecategoryAdapter salecategoryAdapter;
    RelativeLayout bottomNavigation;
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";



    String english = "English";
    String french = "French";


    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        //ChangeLanguage(lang);
        String screen = "HOME";
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Screen, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(lastScreen, screen);
        editor.commit();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        recyclerView = view.findViewById(R.id.subcategory1View);
        test = view.findViewById(R.id.test);
        firstCategory = view.findViewById(R.id.firstCategory);
        secondCategory = view.findViewById(R.id.secondCategory);
        thirdCategory = view.findViewById(R.id.thirdCategory);
        fourthCategory = view.findViewById(R.id.fourthCategory);
        saleCategory = view.findViewById(R.id.salecategory);
        recyclerview3 = view.findViewById(R.id.subcategory3View);
        recyclerview4 = view.findViewById(R.id.subcategory4View);
        recyclerView5 = view.findViewById(R.id.salecategoryview);
        bedroom = view.findViewById(R.id.bedroom);
        livingroom = view.findViewById(R.id.livingroom);
        bathroom = view.findViewById(R.id.bathroom);
        kitchen = view.findViewById(R.id.kitchen);
        office = view.findViewById(R.id.office);
         SearchProduct= view.findViewById(R.id.imgSearch);
         bottomNavigation = view.findViewById(R.id.bottomFragment);


         
         SearchProduct.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(), UserSearchSelectCategory.class);
                 startActivity(intent);
             }
         });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager testLayoutManager = new LinearLayoutManager(getContext());
        testLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        layoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext());
        layoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        test.setLayoutManager(testLayoutManager);
        recyclerview3.setLayoutManager(layoutManager3);
        recyclerview4.setLayoutManager(layoutManager4);
        recyclerView5.setLayoutManager(layoutManager5);

        loadData("BedRoom");
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData("BedRoom");
            }
        });
     livingroom.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             loadData("LivingRoom");
         }
     });
     bathroom.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             loadData("Bathroom");

         }
     });
     kitchen.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             loadData("Kitchen");
         }
     });
     office.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             loadData("Office");

         }
     });
        return view;
    }

    private void loadData( String category) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firstCateogyList = new ArrayList<>();
                secondCategoryList = new ArrayList<>();
                thirdCategoryList = new ArrayList<>();
                fourthCategoryList = new ArrayList<>();
                salecategoryList = new ArrayList<>();

                int count=0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    count ++;
                    if(count==1) {
                        firstCategory.setText(dataSnapshot.getKey());
                    }else if(count==2){
                        secondCategory.setText(dataSnapshot.getKey());
                    }else if(count==3){
                        thirdCategory.setText(dataSnapshot.getKey());
                    }else{
                        fourthCategory.setText(dataSnapshot.getKey());
                    }
                    for (DataSnapshot categoryList : dataSnapshot.getChildren()) {

                        ProductModal listData = categoryList.getValue(ProductModal.class);
                       // listData.id = categoyList.getKey();
                        if(listData.isSale()){
                            salecategoryList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categoryList.getKey(),category,dataSnapshot.getKey()));
                        }
                        if(count==1) {
                            firstCateogyList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categoryList.getKey(),category,dataSnapshot.getKey()));


                        }else if(count==2){
                            secondCategoryList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categoryList.getKey(),category,dataSnapshot.getKey()));

                        }else if(count==3){
                            thirdCategoryList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categoryList.getKey(),category,dataSnapshot.getKey()));

                        }else{
                            fourthCategoryList.add(new ProductModal(listData.getImage(), listData.getName(),listData.getDescription(),listData.getQuality(),listData.getQuantity(),listData.getPrice(),listData.isSale(),listData.getDiscount(),categoryList.getKey(),category,dataSnapshot.getKey()));

                        }

                    }

                }

                if(count==1){
                    secondCategory.setText("");
                    thirdCategory.setText("");
                    fourthCategory.setText("");
                }
                if(count==2){
                    thirdCategory.setText("");
                    fourthCategory.setText("");
                }if(count==3){

                    fourthCategory.setText("");
                }

                firstCategoryAdapter = new subcategory1Adapter(firstCateogyList,getActivity());
                recyclerView.setAdapter(firstCategoryAdapter);

                secondCategoryAdapter = new subcategory2Adapter(secondCategoryList,getActivity());
                test.setAdapter(secondCategoryAdapter);

                thirdCategoryAdapter = new subcategory3Adapter(thirdCategoryList,getActivity());
                recyclerview3.setAdapter(thirdCategoryAdapter);

                fourrthCategoryAdapter = new subcategory4Adapter(fourthCategoryList,getActivity());
                recyclerview4.setAdapter(fourrthCategoryAdapter);

                salecategoryAdapter = new salecategoryAdapter(salecategoryList,getActivity());
                recyclerView5.setAdapter(salecategoryAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to load Products", Toast.LENGTH_SHORT).show();

            }
        });

    }
    /*public void ChangeLanguage(String language){

       // SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor = sharedPreferences.edit();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
    }*/
}