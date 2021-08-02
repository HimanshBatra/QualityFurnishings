package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.OrderModal;
import com.example.qualityfurnishings.model.ProductModal;

import java.util.List;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ViewHolder>{
    private List<OrderModal> userOrderList;
    private List<Cart> cartList;

    Context context;


    public UserOrderAdapter(List<OrderModal> userOrderList , Context context){
        this.userOrderList=userOrderList;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userorderview,parent,false);
        return new UserOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserOrderAdapter.ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(userOrderList.get(position).getCartList().size());
 //       UserOrderListAdapter userOrderListAdapter = new UserOrderListAdapter(userOrderList.get(position).getCartList(),userOrderList.get(position).getOrderId(),userOrderList.get(position).getOrderStatus(), context);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
 //       holder.recyclerView.setAdapter(userOrderListAdapter);
  //      holder.recyclerView.setRecycledViewPool(recycledViewPool);




    }

    @Override
    public int getItemCount() {
        return userOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public ViewHolder( View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.userorderList);

        }
    }
}
