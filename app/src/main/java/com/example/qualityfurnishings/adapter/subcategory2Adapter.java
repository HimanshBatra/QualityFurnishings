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

public class subcategory2Adapter extends RecyclerView.Adapter<subcategory2Adapter.ViewHolder> {
    private List<ProductModal> testModalList;
    Context context;

    public subcategory2Adapter(List<ProductModal> testModalList, Context context) {
        this.testModalList = testModalList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory1view,parent,false);
        return new subcategory2Adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView img_resource = holder.imageView;
        TextView Name = holder.itemName;
        LinearLayout detail=holder.detailscreen;

        Name.setText(testModalList.get(position).name + "");
        Glide.with(context).load(testModalList.get(position).image).into(img_resource);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, DetailActivity.class);
                intent.putExtra("productdata",  testModalList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return testModalList.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView itemName;
         private LinearLayout detailscreen;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             imageView=itemView.findViewById(R.id.imgview);
             itemName=itemView.findViewById(R.id.tv1temName);
             detailscreen=itemView.findViewById(R.id.deatilView);
         }
     }


}
