package com.example.qualityfurnishings.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Locale;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ForgotPasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";*/

    // TODO: Rename and change types of parameters
    //Declaration of Variables
    EditText email;
    Button buttonsend;
    String Semail;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

   FirebaseAuth mAuth;

    // Default Constructor
    public ForgotPasswordFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String lang = sharedPreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_forgot_password, container, false);

        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);


        buttonsend = view.findViewById(R.id.buttonSend);

        mAuth = FirebaseAuth.getInstance();

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = view.findViewById(R.id.editTextEmailId);
                Semail = email.getText().toString();

                if(Patterns.EMAIL_ADDRESS.matcher(Semail).matches()){

                    //auth.fetchProvidersForEmail(email);

                    CheckingWhetherEmailIsPresentOrNot(v);
                }
                else
                {
                    email.requestFocus();
                    email.setError("Enter a valid email"); }

            }
        });

return view;
    }

    //For Sending link to Email


    private void CheckingWhetherEmailIsPresentOrNot(View v) {
        mAuth.fetchSignInMethodsForEmail(Semail)
                .addOnCompleteListener(
                        new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean validation = !task.getResult().getSignInMethods().isEmpty();
                                if (!validation) {
                                    Toast.makeText(getContext(), "User not exists", Toast.LENGTH_SHORT).show()

                                    ;
                                } else {

                                    
                                    AlertDialog.Builder builder
                                            = new AlertDialog
                                            .Builder(getContext());
                                    builder.setMessage("Do you want to send link to your email?");

                                    // Set Alert Title
                                    builder.setTitle("Alert !");
                                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mAuth.sendPasswordResetEmail(Semail)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull  Task<Void> task) {

                                                            getFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();

                                                        }
                                                    });
                                        }
                                    });
                                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            getFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();
                                        }
                                    });
                                    builder.show();

                                   
                                }
                            }
                        });

    }
}