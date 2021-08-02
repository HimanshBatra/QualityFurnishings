package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.OrderModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {
    @NonNull

    private List<OrderModal> adminOrderList;
    private List<Cart> cartList;
    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    Context context;
    String userid;

    String orderKey;

    public AdminOrderAdapter(List<OrderModal> adminOrderList , Context context){
        this.adminOrderList=adminOrderList;
        this.context=context;

    }
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminorderlayout,parent,false);

        return new AdminOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder holder, int position) {
        Button btAcceptOrder = holder.accept;
        Button btDeclineOrder = holder.decline;
        TextView paymentMethod = holder.paymentType;
        paymentMethod.setText(adminOrderList.get(position).getPaymentMethod());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.orderlistRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(adminOrderList.get(position).getCartList().size());
        AdminOrderListAdapter adminOrderListAdapter = new AdminOrderListAdapter(adminOrderList.get(position).getCartList(), context);
        holder.orderlistRecyclerView.setLayoutManager(linearLayoutManager);
        holder.orderlistRecyclerView.setAdapter(adminOrderListAdapter);
        holder.orderlistRecyclerView.setRecycledViewPool(recycledViewPool);

        btAcceptOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartList = adminOrderList.get(position).getCartList();
                userid = cartList.get(0).getUserid();
                orderKey = adminOrderList.get(position).getKey();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FurnitureCategory")
                        .child("Orders").child(userid).child(orderKey).child("orderStatus");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String status = (String) dataSnapshot.getValue();
//                        Log.d("TAG", "status "+status);

                        databaseReference.setValue("Accepted");
                        adminOrderList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,adminOrderList.size());



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
        btDeclineOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartList = adminOrderList.get(position).getCartList();
                userid = cartList.get(0).getUserid();
                orderKey = adminOrderList.get(position).getKey();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FurnitureCategory")
                        .child("Orders").child(userid).child(orderKey).child("orderStatus");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String status = (String) dataSnapshot.getValue();
                        Log.d("TAG", "status "+status);

                        databaseReference.setValue("Rejected");
                        adminOrderList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,adminOrderList.size());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return adminOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView orderlistRecyclerView;
        Button accept,decline;
        TextView paymentType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accept=itemView.findViewById(R.id.btAcceptOrder);
            decline=itemView.findViewById(R.id.btDeclineOrder);
            orderlistRecyclerView=itemView.findViewById(R.id.ordersList);
            paymentType=itemView.findViewById(R.id.tvPaymentType);
        }
    }
}
