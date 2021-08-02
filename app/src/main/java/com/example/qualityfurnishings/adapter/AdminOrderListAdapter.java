package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;

import java.util.List;

public class AdminOrderListAdapter extends RecyclerView.Adapter<AdminOrderListAdapter.ViewHolder> {
    private List<Cart> cartList;

    Context context;

    public AdminOrderListAdapter(List<Cart> cartList, Context context){
        this.cartList=cartList;
        this.context=context;



    }
    @Override
    public AdminOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist,parent,false);
        return new AdminOrderListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AdminOrderListAdapter.ViewHolder holder, int position) {
        ImageView imgValue= holder.productImage;
        TextView name= holder.productName;
        TextView price= holder.productPrice;
        TextView quantity= holder.quantity;


        Glide.with(context).load(cartList.get(position).image).into(imgValue);
        name.setText(cartList.get(position).productName);
        price.setText(cartList.get(position).finalPrice +"");
        quantity.setText(cartList.get(position).quantity +"");


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName,productPrice,quantity;
        public ViewHolder( View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.imgProduct);
            productName=itemView.findViewById(R.id.tvproductName);
            productPrice=itemView.findViewById(R.id.tvproductPrice);
            quantity=itemView.findViewById(R.id.tvQuantity);

        }
    }
}
