package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        ImageView img_resource = holder.productImage;
        TextView id = holder.orderId;
        TextView status = holder.orderStatus;
        TextView totalamount = holder.Amount;

        cartList=userOrderList.get(position).getCartList();
        Glide.with(context).load(cartList.get(position).image).into(img_resource);
        id.setText(userOrderList.get(position).getOrderId()+"");
        status.setText(userOrderList.get(position).getOrderStatus());
        totalamount.setText(cartList.get(position).getFinalPrice()+"");



    }

    @Override
    public int getItemCount() {
        return userOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView orderId,orderStatus,Amount;
        public ViewHolder( View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.productImage);
            orderId=itemView.findViewById(R.id.tvorderId);
            orderStatus=itemView.findViewById(R.id.tvorderStatus);
            Amount=itemView.findViewById(R.id.tvTotalAmount);
        }
    }
}
