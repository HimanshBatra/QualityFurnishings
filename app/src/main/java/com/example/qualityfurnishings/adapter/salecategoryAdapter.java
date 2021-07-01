package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.ProductModal;

import java.util.List;

public class salecategoryAdapter extends RecyclerView.Adapter<salecategoryAdapter.ViewHolder> {
    private List<ProductModal> salecategoryList;
    Context context;

    public salecategoryAdapter(List<ProductModal> salecategoryList , Context context){
        this.salecategoryList=salecategoryList;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salecategoryview,parent,false);
        return new salecategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView img_resource = holder.imageView;
        TextView Name = holder.itemName;

        Name.setText(salecategoryList.get(position).name + "");
        Glide.with(context).load(salecategoryList.get(position).image).into(img_resource);
    }

    @Override
    public int getItemCount() {
        return salecategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgview);
            itemName = itemView.findViewById(R.id.tv1temName);
        }
    }
}
