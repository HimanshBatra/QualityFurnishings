package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.Favouties;
import com.example.qualityfurnishings.model.OrderModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FavouriteProductsAdapter extends RecyclerView.Adapter<FavouriteProductsAdapter.ViewHolder> {
    private List<Favouties> favouritiesList;
    Context context;
    String userid;

    public FavouriteProductsAdapter(List<Favouties> favouritiesList , Context context){
        this.favouritiesList=favouritiesList;
        this.context=context;

    }
    @Override
    public FavouriteProductsAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favproductlayout,parent,false);

        return new FavouriteProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( FavouriteProductsAdapter.ViewHolder holder, int position) {
        ImageView imgProduct = holder.productImage;
        ImageView likeProduct = holder.likeProduct;
        TextView tvProductName = holder.productName;
        Glide.with(context).load(favouritiesList.get(position).getImage()).into(imgProduct);
        tvProductName.setText(favouritiesList.get(position).getProductName()+"");
        likeProduct.setBackgroundResource(R.drawable.like);
        userid = favouritiesList.get(position).getUserid();
        likeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("FurnitureCategory").child("Favourities").child(userid).child(favouritiesList.get(position).getId()).removeValue();
                favouritiesList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,favouritiesList.size());
            }
        });



    }

    @Override
    public int getItemCount() {
        return favouritiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        ImageView likeProduct;
        TextView productName;
        public ViewHolder( View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.favProductImage);
            likeProduct=itemView.findViewById(R.id.imgFavButton);
            productName=itemView.findViewById(R.id.tvfavProductName);
        }
    }
}
