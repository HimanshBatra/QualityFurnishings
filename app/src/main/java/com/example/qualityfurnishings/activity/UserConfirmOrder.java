package com.example.qualityfurnishings.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;

public class UserConfirmOrder extends AppCompatActivity {
    TextView totalAmount;
    EditText creditcard , month, year,cvv;
    Button ConfirmOrder;
    int amount;
    RadioGroup radioGroup;
    RadioButton Rcredit,RCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_order);
        Bundle extras = getIntent().getExtras();
        amount=extras.getInt("amountValue");

        totalAmount=(TextView)findViewById(R.id.tvTotalAmount);
        creditcard=(EditText)findViewById(R.id.etCardNumber);
        month=(EditText)findViewById(R.id.etMonth);
        year=(EditText)findViewById(R.id.etYear);
        cvv=(EditText)findViewById(R.id.etCvv);
        ConfirmOrder=(Button)findViewById(R.id.btConfirmOrder);
        totalAmount.setText(amount+"");
        radioGroup=(RadioGroup)findViewById(R.id.radioPayment);
        Rcredit=(RadioButton) findViewById(R.id.radioCredit);
        RCash=(RadioButton) findViewById(R.id.radioCash);
        //

        creditcard.setHint("Credit Card Number");
        month.setHint("Month");
        year.setHint("Year");
        cvv.setHint("CVV");


        creditcard.setVisibility(View.GONE);
        month.setVisibility(View.GONE);
        year.setVisibility(View.GONE);
        cvv.setVisibility(View.GONE);

        Rcredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    creditcard.setVisibility(View.VISIBLE);
                    month.setVisibility(View.VISIBLE);
                    year.setVisibility(View.VISIBLE);
                    cvv.setVisibility(View.VISIBLE);
                    creditcard.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_credit_card_24, 0, 0, 0);
                }
                else{
                    creditcard.setVisibility(View.GONE);
                    month.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    cvv.setVisibility(View.GONE);
                }

            }
        });

        RCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    creditcard.setVisibility(View.GONE);
                    month.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    cvv.setVisibility(View.GONE);
                }

            }
        });

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_LONG).show();
            }
        });


    }
}