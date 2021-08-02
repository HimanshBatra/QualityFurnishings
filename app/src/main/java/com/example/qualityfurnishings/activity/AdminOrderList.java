package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.adapter.AdminOrderAdapter;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.OrderModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOrderList extends AppCompatActivity {
    RecyclerView adminordersView;
    ArrayList<OrderModal> orderlist;
    ArrayList<Cart> cartlist;
    AdminOrderAdapter adminOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);
        adminordersView=(RecyclerView)findViewById(R.id.productsOrderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adminordersView.setLayoutManager(layoutManager);
        orderlist = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot data : dataSnapshot.getChildren()) {

                        OrderModal listData = data.getValue(OrderModal.class);

                        if (listData.getOrderStatus().equals("Pending")) {
                            orderlist.add(new OrderModal(listData.getOrderId(), listData.getOrderStatus(), listData.getPaymentMethod(), listData.getCartList(), listData.getKey()));
                        }
                    }





                }
                adminOrderAdapter = new AdminOrderAdapter(orderlist,getApplicationContext());
                adminordersView.setAdapter(adminOrderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}