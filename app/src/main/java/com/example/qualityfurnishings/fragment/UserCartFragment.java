package com.example.qualityfurnishings.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.CartAdapter;
import com.example.qualityfurnishings.adapter.subcategory1Adapter;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class UserCartFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Cart> cartlist;
    CartAdapter cartAdapter;





    public UserCartFragment() {
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
        View view= inflater.inflate(R.layout.fragment_user_cart, container, false);
        recyclerView = view.findViewById(R.id.usercartView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPref", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("userid","");
        cartlist = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(s1).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    Cart listData = dataSnapshot.getValue(Cart.class);
                    cartlist.add(new Cart(listData.getProductName(),listData.getImage(),listData.getCategory(),listData.getSubcategory(),listData.getQuantity(),listData.getFinalPrice()));


                }
                cartAdapter = new CartAdapter(getActivity(), cartlist);
                recyclerView.setAdapter(cartAdapter);

            }


            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        return view;


    }
}