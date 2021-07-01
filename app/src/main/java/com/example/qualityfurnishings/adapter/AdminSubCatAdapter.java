package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.SubCatrgory1Modal;

import java.util.List;

public class AdminSubCatAdapter extends RecyclerView.Adapter<AdminSubCatAdapter.ViewHolder> {
    private List<SubCatrgory1Modal> adminsubcategorylist;
    Context context;

    public AdminSubCatAdapter(List<SubCatrgory1Modal> adminsubcategorylist , Context context){
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
        Name.setText(adminsubcategorylist.get(position).name+"");
        Glide.with(context).load(adminsubcategorylist.get(position).image).into(img_resource);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
