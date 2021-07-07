
package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cart> list;


    public CartAdapter(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
        //this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        Cart cart = list.get(position);
        holder.name.setText(cart.getProductName());
        holder.price.setText(cart.getFinalPrice());

        holder.quantity.setText("1");

    }


    @Override
    public int getItemCount() {
        return list.size();
        //return list1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,quantity;
        ImageView productImage;
        ImageButton plus,minus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.Price);
            quantity = itemView.findViewById(R.id.Quantity);

            // productImage = itemView.findViewById(R.id.productImage);
            //plus = itemView.findViewById(R.id.Plus);
            //minus = itemView.findViewById(R.id.Minus);


        }
    }



}


