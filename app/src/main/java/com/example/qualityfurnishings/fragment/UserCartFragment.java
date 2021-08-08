package com.example.qualityfurnishings.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.UserConfirmOrder;
import com.example.qualityfurnishings.activity.UserHome;
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
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class UserCartFragment extends Fragment  {
    private RecyclerView recyclerView;
    ArrayList<Cart> cartlist;
    CartAdapter cartAdapter;
    TextView total,TvNull;
    Button checkout;
    String stpostalcode,staddress,stprovince;
    int totalPrice;
    public static final String Screen = "UserScreen" ;
    public static final String lastScreen = "lastscreen";
    String addressbox ;
    String houseNumber;
    String postalcode;
    String province;
    String c;
    String y;
    String a;
    String addressNotAvailiable;
    String alertForCheckout;



    public UserCartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        addressbox =getResources().getString(R.string.cya);
        houseNumber=getResources().getString(R.string.hn);
        postalcode=getResources().getString(R.string.postalcode);
        province=getResources().getString(R.string.province);
        c=getResources().getString(R.string.cancel);
        y=getResources().getString(R.string.yes);
        a=getResources().getString(R.string.alert);
        addressNotAvailiable=getResources().getString(R.string.ana);
        alertForCheckout=getResources().getString(R.string.checkoutAlert);

        String screen = "Cart";
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Screen, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString(lastScreen, screen);
        editor1.commit();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_cart, container, false);
        recyclerView = view.findViewById(R.id.usercartView);
        total = view.findViewById(R.id.total);
        TvNull = view.findViewById(R.id.tvNull);
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
                    cartlist.add(new Cart(listData.getProductName(),listData.getImage(),listData.getCategory(),listData.getSubcategory(),listData.getQuantity(),listData.getFinalPrice(),dataSnapshot.getKey(),listData.getUserid(),listData.getProductPrice(),listData.getItemCount()));
                    totalPrice=totalPrice+listData.getFinalPrice();

                }
                if (cartlist.isEmpty()){
                    TvNull.setVisibility(View.VISIBLE);
                }
                else {
                    TvNull.setVisibility(View.GONE);
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
                databaseReference.child("users").child(s1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserTestModal usermodel = snapshot.getValue(UserTestModal.class);
                        stpostalcode = usermodel.getPostalcode();
                        staddress = usermodel.getAddress();
                        stprovince = usermodel.getProvince();
                        if (totalPrice == 0) {

                            Toast.makeText(getContext(), alertForCheckout, Toast.LENGTH_LONG).show();

                        }


                        else if (stpostalcode.equals("") || staddress.equals("") || stprovince.equals("")) {
//                            Toast.makeText(getContext(), "Please Add your address in your Profile before Checkout", Toast.LENGTH_SHORT).show();


                            // setup the alert builder
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(a);
                            builder.setMessage(addressNotAvailiable);

                            // add a button
                            builder.setPositiveButton(y, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Activity activity = getActivity();
                                    if (activity instanceof UserHome) {
                                        ((UserHome) activity).showUserProfile();
                                    }

                                }
                            });
                            builder.setNegativeButton(c, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
//                                    getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserCartFragment()).commit();
                                        }

                                    });


                            builder.show();


                        }
                        else {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                            builder2.setMessage(houseNumber + staddress + "\n"+postalcode +stpostalcode +"\n"+ province +stprovince);
                            builder2.setTitle(addressbox);
                            builder2.setPositiveButton(y, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent= new Intent(getContext(),UserConfirmOrder.class);
                                    intent.putExtra("amountValue",totalPrice);
                                    startActivity(intent);



                                }


                                });
                            builder2.setNegativeButton(c, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
//                                    getFragmentManager().beginTransaction().replace(R.id.UserHomeFrame, new UserCartFragment()).commit();
                                        }
                                    });
                            AlertDialog alertdialog = builder2.create();
                            alertdialog.show();






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