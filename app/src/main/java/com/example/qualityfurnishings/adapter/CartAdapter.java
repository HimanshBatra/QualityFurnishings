
package com.example.qualityfurnishings.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.AdminModal;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cart> cartlist = new ArrayList<Cart>();
    String userID;
    String key;
    FirebaseAuth firebaseAuth;
    int ItemCount, presentQuantity;




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
        ImageView implus=holder.plus;
        ImageView imMinus=holder.minus;
        ImageView imDelete=holder.delete;
        firebaseAuth= FirebaseAuth.getInstance();
        String firebaseuserid = firebaseAuth.getCurrentUser().getUid();




        Glide.with(context).load(cartlist.get(position).image).into(imgView);
        productName.setText(cartlist.get(position).productName + "");
        productPrice.setText(cartlist.get(position).finalPrice + "");
        productquantity.setText(cartlist.get(position).quantity + "");
        productsubcategory.setText(cartlist.get(position).subcategory + "");



        imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("FurnitureCategory").child("Cart").child(firebaseuserid).child(cartlist.get(position).getId()).removeValue();
                cartlist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cartlist.size());
            }
        });

        implus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemCount=cartlist.get(position).itemCount;
                Log.d("item", ItemCount+"");
                presentQuantity=cartlist.get(position).getQuantity();
                Log.d("item1", presentQuantity+"");
                if(presentQuantity!=ItemCount){
                    int final_price=(cartlist.get(position).getQuantity() +1) * cartlist.get(position).getProductPrice();
                    Cart updateCart =new Cart(cartlist.get(position).getProductName(),cartlist.get(position).getImage(),cartlist.get(position).getCategory(),cartlist.get(position).getSubcategory(),cartlist.get(position).getQuantity()+1,final_price,cartlist.get(position).getId(),cartlist.get(position).getUserid(),cartlist.get(position).getProductPrice(),cartlist.get(position).getItemCount());
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("FurnitureCategory").child("Cart").child(firebaseuserid).child(cartlist.get(position).getId()).setValue(updateCart);

                }
                else{
                    Toast.makeText(context.getApplicationContext(),"Sorry!",Toast.LENGTH_LONG).show();



                }

               // databaseReference.child("users").child(cartlist.get(position).getUserid()).child("cart").child(cartlist.get(position).getId()).child("finalPrice").setValue(cartlist.get(position).getQuantity()* cartlist.get(position).getProductPrice());
            }
        });

        imMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartlist.get(position).getQuantity() != 1) {
                    int final_price = (cartlist.get(position).getQuantity() - 1) * cartlist.get(position).getProductPrice();
                    Cart updateCart = new Cart(cartlist.get(position).getProductName(), cartlist.get(position).getImage(), cartlist.get(position).getCategory(), cartlist.get(position).getSubcategory(), cartlist.get(position).getQuantity() - 1, final_price, cartlist.get(position).getId(), cartlist.get(position).getUserid(), cartlist.get(position).getProductPrice(),cartlist.get(position).getItemCount());

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("FurnitureCategory").child("Cart").child(firebaseuserid).child(cartlist.get(position).getId()).setValue(updateCart);
                    // databaseReference.child("users").child(cartlist.get(position).getUserid()).child("cart").child(cartlist.get(position).getId()).child("finalPrice").setValue(cartlist.get(position).getQuantity()*cartlist.get(position).getProductPrice());
                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return cartlist.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,quantity,subcategory;
        ImageView productImage,plus,minus,delete;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.Quantity);
            subcategory = itemView.findViewById(R.id.subcategory);
            productImage=itemView.findViewById(R.id.productImage);
            plus=itemView.findViewById(R.id.Plus);
            minus=itemView.findViewById(R.id.Minus);
            delete=itemView.findViewById(R.id.delete);





        }
    }



}


