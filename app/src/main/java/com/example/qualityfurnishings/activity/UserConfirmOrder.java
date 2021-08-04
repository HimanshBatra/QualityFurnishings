package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.SignUp;
import com.example.qualityfurnishings.model.Cart;
import com.example.qualityfurnishings.model.Favouties;
import com.example.qualityfurnishings.model.OrderModal;
import com.example.qualityfurnishings.model.ProductModal;
import com.example.qualityfurnishings.model.TokenModal;
import com.example.qualityfurnishings.model.UserTestModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserConfirmOrder extends AppCompatActivity {
    TextView totalAmount, month, year;
    EditText creditcard, cvv;
    Button ConfirmOrder;
    int amount;
    RadioGroup radioGroup;
    String fcm;
    RadioButton Rcredit, RCash;
    String stCredit, stMonth, stYear, stCvv;
    boolean boolCredit, boolCash;
    int i = 16;
    DatePickerDialog datePickerDialog;
    int intDate, intMonth, intYear;
    String stCategory, stID, stImage, stProductName, stSubcategory, stUserid;
    int stFinalPrice, stProductPrice, stQuantity;
    List<Cart> cartList;
    String OrderStatus, paymentMethod;
    String FirebaseUserID;
    int count,OrderId;
    String stOrderId;
    String orderKey;
    String title="Order";
    String messege="A New order";

    SharedPreferences sharedPreferencesusertype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_order);
        Bundle extras = getIntent().getExtras();
        amount = extras.getInt("amountValue");
        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE);
        FirebaseUserID = sharedPreferences.getString("userid", "");
        OrderStatus = "Pending";
         sharedPreferencesusertype = getSharedPreferences("LoginPref", MODE_PRIVATE);





        totalAmount = (TextView) findViewById(R.id.tvTotalAmount);
        creditcard = (EditText) findViewById(R.id.etCardNumber);
        month = (TextView) findViewById(R.id.etMonth);
        year = (TextView) findViewById(R.id.etYear);
        cvv = (EditText) findViewById(R.id.etCvv);
        ConfirmOrder = (Button) findViewById(R.id.btConfirmOrder);
        totalAmount.setText(amount + "");
        radioGroup = (RadioGroup) findViewById(R.id.radioPayment);
        Rcredit = (RadioButton) findViewById(R.id.radioCredit);
        RCash = (RadioButton) findViewById(R.id.radioCash);


        month.setOnClickListener(new View.OnClickListener() {
            //            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                selectDate();

            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        // setting the hint in edit text
        creditcard.setHint("Credit Card Number");
        cvv.setHint("CVV");

        //set the values in string


        creditcard.setVisibility(View.GONE);
        month.setVisibility(View.GONE);
        year.setVisibility(View.GONE);
        cvv.setVisibility(View.GONE);

        Rcredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean credit) {
                if (credit == true) {
                    boolCredit = true;
                    creditcard.setVisibility(View.VISIBLE);
                    month.setVisibility(View.VISIBLE);
                    year.setVisibility(View.VISIBLE);
                    cvv.setVisibility(View.VISIBLE);
                    creditcard.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_credit_card_24, 0, 0, 0);
                } else {
                    boolCredit = false;
                    creditcard.setVisibility(View.GONE);
                    month.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    cvv.setVisibility(View.GONE);
                }

            }
        });

        RCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean cash) {
                if (cash == true) {
                    boolCash = true;
                    creditcard.setVisibility(View.GONE);
                    month.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    cvv.setVisibility(View.GONE);


                } else {
                    boolCash = false;
                }

            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("FurnitureCategory").child("Cart").child(FirebaseUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                cartList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Cart cartmodel = dataSnapshot.getValue(Cart.class);
                    cartList.add(new Cart(cartmodel.getProductName(), cartmodel.getImage(), cartmodel.getCategory(), cartmodel.getSubcategory(), cartmodel.getQuantity(), cartmodel.getFinalPrice(), cartmodel.getId(), cartmodel.getUserid(), cartmodel.getProductPrice(),cartmodel.getItemCount()));

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stCredit = creditcard.getText().toString();
                stMonth = month.getText().toString();
                stYear = year.getText().toString();
                stCvv = cvv.getText().toString();

//                if (!Rcredit.isChecked()) {
//                    if (!RCash.isChecked()) {
//                        Toast.makeText(getApplicationContext(), "Please Select a Payment type", Toast.LENGTH_LONG).show();
//                    }
//                }

                if (Rcredit.isChecked()) {

                    if (stCredit == null) {
                        creditcard.setError("Invalid Card Number");
                    } else if (stCredit.length() != i) {
                        Log.d("st", String.valueOf(stCredit.length() + stCredit));
                        creditcard.setError("Invalid Card Number");
                    } else if (stMonth == "") {

                        Toast.makeText(getApplicationContext(), "Please Select a month ", Toast.LENGTH_LONG).show();
                    } else if (stYear == "") {

                        Toast.makeText(getApplicationContext(), "Please Select a year ", Toast.LENGTH_LONG).show();
                    } else if (stCvv.length() != 3) {
                        cvv.setError("Invalid CVV");
                    } else {
                        paymentMethod = "Credit Card";
                        orderConfirmed();
                    }

                } else if (RCash.isChecked()) {
                    paymentMethod = "Cash On Delivery";
                    orderConfirmed();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Select a Payment type", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void orderConfirmed() {

        final List<OrderModal> OrderList = new ArrayList<>(); // list of groups
        FirebaseDatabase.getInstance()
                .getReference()
                .child("FurnitureCategory")
                .child("Orders") // this is the id of user - over whose groups you want to iterate
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for(DataSnapshot orderCount : snapshot.getChildren()){
                                OrderModal order = orderCount.getValue(OrderModal.class); // this is a group
                                OrderList.add(order);


                            }

                        }
                        count = OrderList.size();
//                        Log.d("size", String.valueOf(count));
                        count = count+1;
                        OrderId=count;
                        stOrderId = String.valueOf(OrderId);
//                        Log.d("size2", String.valueOf(OrderId));
                        placeOrder();





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




    }

    private void placeOrder() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference db_refOrders = databaseReference.child("FurnitureCategory").child("Orders").child(FirebaseUserID).push();


        orderKey = db_refOrders.getKey();
        OrderModal order = new OrderModal(stOrderId, OrderStatus, paymentMethod, cartList,orderKey);

        db_refOrders.setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                Toast.makeText(UserConfirmOrder.this, "Order Completed Successfully", Toast.LENGTH_LONG).show();
                DatabaseReference df = FirebaseDatabase.getInstance().getReference();
                df.child("FurnitureCategory").child("Cart").child(FirebaseUserID).removeValue();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FurnitureCategory")
                        .child("Token");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<TokenModal> tokenlist;
                        tokenlist = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TokenModal listData = snapshot.getValue(TokenModal.class);
                            tokenlist.add(new TokenModal(listData.getToken(),listData.getId()));
                            fcm=listData.getToken();

                        }
                        Log.d("chl", fcm);

//                       fcm = (String) dataSnapshot.getValue();





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                String s1 = sharedPreferencesusertype.getString("usertype", "");
                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender(fcm, title, messege, getApplicationContext(), UserConfirmOrder.this);
                    notificationsSender.SendNotifications();

                Intent intent = new Intent(getApplicationContext(), UserHome.class);
                startActivity(intent);
            }

        });

    }


    private void selectDate() {
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(UserConfirmOrder.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int tyear, int tmonth, int tdayOfMonth) {
                intDate=tdayOfMonth;
                intMonth=tmonth+1;
                intYear=tyear;
                stMonth= String.valueOf(intMonth);
                stYear= String.valueOf(intYear);
                month.setText(stMonth);
                year.setText(stYear);
            }

        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();

    }
}