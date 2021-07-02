package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminAddProducts;
import com.example.qualityfurnishings.activity.EditProducts;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminSubCatAdapter extends RecyclerView.Adapter<AdminSubCatAdapter.ViewHolder> {
    private List<ProductModal> adminsubcategorylist;
    Context context;

    public AdminSubCatAdapter(List<ProductModal> adminsubcategorylist , Context context){
        this.adminsubcategorylist=adminsubcategorylist;
        this.context=context;

    }
    @NonNull
    @Override
    public AdminSubCatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminsubcategory,parent,false);
        return new AdminSubCatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSubCatAdapter.ViewHolder holder, int position) {
        ImageView img_resource = holder.imageView;
        TextView Name = holder.itemName;
        ImageView edit=holder.imageEdit;
        ImageView delete=holder.imageDelete;

        Name.setText(adminsubcategorylist.get(position).getName());
        Glide.with(context).load(adminsubcategorylist.get(position).image).into(img_resource);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference();
                databaseReference.child("FurnitureCategory").child(adminsubcategorylist.get(position).getCategory()).child(adminsubcategorylist.get(position).getSubCategory()).child(adminsubcategorylist.get(position).getId()).removeValue();

                adminsubcategorylist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,adminsubcategorylist.size());

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, EditProducts.class);
                intent.putExtra("productdata",  adminsubcategorylist.get(position));
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return adminsubcategorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView imageDelete;
        private ImageView imageEdit;
        private TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.subimage);
            imageDelete = (ImageView)itemView.findViewById(R.id.imgDelete);
            imageEdit = (ImageView)itemView.findViewById(R.id.imgEdit);
            itemName = (TextView) itemView.findViewById(R.id.subname);


        }
    }
}
