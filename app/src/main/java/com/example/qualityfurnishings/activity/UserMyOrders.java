package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.voice.AlwaysOnHotwordDetector;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.UserOrderAdapter;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.OrderModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserMyOrders extends AppCompatActivity {
     RecyclerView orderView;
    ArrayList<OrderModal> orderlist;
    ArrayList<Cart> cartlist;
    UserOrderAdapter userOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_my_orders);
        orderView=(RecyclerView)findViewById(R.id.userOrderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        orderView.setLayoutManager(layoutManager);
        orderlist = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Orders").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    OrderModal listData = dataSnapshot.getValue(OrderModal.class);
                    orderlist.add(new OrderModal(listData.getOrderId(),listData.getOrderStatus(),listData.getPaymentMethod(),listData.getCartList()));


                }
                userOrderAdapter = new UserOrderAdapter(orderlist,getApplicationContext());
                orderView.setAdapter(userOrderAdapter);


            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }
}