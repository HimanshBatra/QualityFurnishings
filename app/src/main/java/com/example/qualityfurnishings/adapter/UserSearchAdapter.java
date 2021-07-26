package com.example.qualityfurnishings.adapter;

import android.content.Context;
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
import com.example.qualityfurnishings.User;
import com.example.qualityfurnishings.activity.UserSearchScreen;
import com.example.qualityfurnishings.model.ProductModal;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.ViewHolder> {
    private List<ProductModal> productList;
    Context context;

    public UserSearchAdapter(List<ProductModal> productList, Context applicationContext) {
        this.productList=productList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usersearchlayout,parent,false);
        return new UserSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserSearchAdapter.ViewHolder holder, int position) {
        ImageView img_Value= holder.productimageView;
        TextView ProductName = holder.productName;
        TextView ProductSubCategory = holder.productCategory;
//        LinearLayout View=holder.searchproductLayout;

        Glide.with(UserSearchScreen.context).load(productList.get(position).image).into(img_Value);
        ProductName.setText(productList.get(position).getName()+"");
        ProductSubCategory.setText(productList.get(position).getSubCategory()+"");




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productimageView;
        private TextView productName;
        private TextView productCategory;
//        private LinearLayout searchproductLayout;
        public ViewHolder( View itemView) {
            super(itemView);
            productimageView=itemView.findViewById(R.id.proImage);
            productName=itemView.findViewById(R.id.tvitemName);
            productCategory=itemView.findViewById(R.id.tvsubCategory);
//            searchproductLayout=itemView.findViewById(R.id.searchProduct);
        }
    }
}
