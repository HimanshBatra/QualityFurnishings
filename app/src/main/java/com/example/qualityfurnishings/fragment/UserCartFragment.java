package com.example.qualityfurnishings.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.UserConfirmOrder;
import com.example.qualityfurnishings.adapter.CartAdapter;
import com.example.qualityfurnishings.adapter.subcategory1Adapter;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.ProductModal;
import com.example.qualityfurnishings.model.UserTestModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class UserCartFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Cart> cartlist;
    CartAdapter cartAdapter;
    TextView total;
    Button checkout;
    String stpostalcode,staddress,stprovince;
    int totalPrice;




    public UserCartFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_cart, container, false);
        recyclerView = view.findViewById(R.id.usercartView);
        total = view.findViewById(R.id.total);
        checkout=view.findViewById(R.id.btCheckout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPref", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("userid","");
        cartlist = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("FurnitureCategory").child("Cart").child(s1).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartlist.clear();
                totalPrice=0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    Cart listData = dataSnapshot.getValue(Cart.class);
                    cartlist.add(new Cart(listData.getProductName(),listData.getImage(),listData.getCategory(),listData.getSubcategory(),listData.getQuantity(),listData.getFinalPrice(),dataSnapshot.getKey(),listData.getUserid(),listData.getProductPrice()));
                    totalPrice=totalPrice+listData.getFinalPrice();

                }
                total.setText(totalPrice+"");
                cartAdapter = new CartAdapter(getActivity(), cartlist);
                recyclerView.setAdapter(cartAdapter);

            }


            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("users").child(s1).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserTestModal usermodel = snapshot.getValue(UserTestModal.class);
                        stpostalcode = usermodel.getPostalcode();
                        staddress = usermodel.getAddress();
                        stprovince = usermodel.getProvince();
                        if (totalPrice == 0) {
                            Toast.makeText(getContext(), "Please add some products before checkout", Toast.LENGTH_LONG).show();
                            return;
                        }


                        else if (stpostalcode.equals("") || staddress.equals("") || stprovince.equals("")) {
//                            Toast.makeText(getActivity(), "Please Add your address in your Profile before Checkout", Toast.LENGTH_SHORT).show();

                            // setup the alert builder
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("ALERT");
                            builder.setMessage("Please Add your address in your Profile before Checkout");

                            // add a button
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserHomeFragment()).commit();
                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserCartFragment()).commit();
                                }
                            });


                            // create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Confirm Your Address");
                            builder.setMessage("House Number: " + staddress + "\n"+"Postal Code: " +stpostalcode +"\n"+ "Province: " +stprovince);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getContext(), UserConfirmOrder.class);
                                    intent.putExtra("amountValue", totalPrice);
                                    intent.putExtra("cartValue", cartlist);
                                    startActivity(intent);

                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserCartFragment()).commit();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;


    }
}