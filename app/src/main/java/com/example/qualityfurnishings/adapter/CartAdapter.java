
package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cart> cartlist = new ArrayList<Cart>();




    public CartAdapter(Context context, ArrayList<Cart> cartlist ) {
        this.context = context;
        this.cartlist = cartlist;
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
        ImageView imgView=holder.productImage;
        TextView productName= holder.name;
        TextView productPrice=holder.price;
        TextView productquantity= holder.quantity;
        TextView productsubcategory= holder.subcategory;

        Glide.with(context).load(cartlist.get(position).image).into(imgView);
        productName.setText(cartlist.get(position).productName + "");
        productPrice.setText(cartlist.get(position).finalPrice + "");
        productquantity.setText(cartlist.get(position).quantity + "");
        productsubcategory.setText(cartlist.get(position).subcategory + "");

//        ImageView img_resource = holder.productImage;
//        Cart cart = list.get(position);
//        holder.name.setText(cart.getProductName());
//        holder.price.setText(cart.getFinalPrice());
//        holder.quantity.setText("1");
//        Glide.with(context).load(list.get(position).getImage()).into(img_resource);

    }


    @Override
    public int getItemCount() {
        return cartlist.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,quantity,subcategory;
        ImageView productImage;
        ImageButton plus,minus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.Quantity);
            subcategory = itemView.findViewById(R.id.subcategory);
            productImage=itemView.findViewById(R.id.productImage);




        }
    }



}


