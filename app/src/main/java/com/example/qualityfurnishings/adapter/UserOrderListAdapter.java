package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import java.util.Locale;

public class UserOrderListAdapter extends RecyclerView.Adapter<UserOrderListAdapter.ViewHolder>{
    private List<Cart> cartList;
    Context context;
    String storderId, storderStatus;
    SharedPreferences sharedPreferences;

    public UserOrderListAdapter(List<Cart> cartList, String orderId, String orderStatus, Context context){
        this.cartList=cartList;
        this.context=context;
        this.storderId=orderId;
        this.storderStatus=orderStatus;
        storderId = orderId;
        storderStatus = orderStatus;



    }
    @Override
    public UserOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* sharedPreferences = context.getSharedPreferences("LANGUAGE",Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());*/

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userorderlist,parent,false);
        return new UserOrderListAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder( UserOrderListAdapter.ViewHolder holder, int position) {
        ImageView img_resource = holder.productImage;
        TextView id = holder.orderId;
        TextView status = holder.orderStatus;
        TextView totalamount = holder.Amount;

        Glide.with(context).load(cartList.get(position).image).into(img_resource);
        totalamount.setText(cartList.get(position).finalPrice +"");
        id.setText(storderId);
        status.setText(storderStatus);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView orderId,orderStatus,Amount;
        public ViewHolder( View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.productImage);
            orderId=itemView.findViewById(R.id.tvorderId);
            orderStatus=itemView.findViewById(R.id.tvorderStatus);
            Amount=itemView.findViewById(R.id.tvTotalAmount);
        }
    }
}
