package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.DetailActivity;
import com.example.qualityfurnishings.model.ProductModal;

import java.util.List;

public class subcategory1Adapter extends RecyclerView.Adapter<subcategory1Adapter.Viewholder> {
    private List<ProductModal> modelClassList;
    Context context;
    public subcategory1Adapter(List<ProductModal> modelClassList, Context context) {
        this.modelClassList = modelClassList;
        this.context = context;
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView itemName;
        private LinearLayout detailscreen;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgview);
            itemName=itemView.findViewById(R.id.tv1temName);
            detailscreen=itemView.findViewById(R.id.deatilView);

        }

    }

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory1view,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ImageView img_resource = holder.imageView;
        TextView Name = holder.itemName;
        LinearLayout detail=holder.detailscreen;

        Name.setText(modelClassList.get(position).name + "");
        Glide.with(context).load(modelClassList.get(position).image).into(img_resource);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });

    }




    public int getItemCount() {
        return modelClassList.size();
    }
}
