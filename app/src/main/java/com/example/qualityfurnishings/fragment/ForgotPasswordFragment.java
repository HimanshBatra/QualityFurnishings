package com.example.qualityfurnishings.fragment;

import android.content.Intent;
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

   FirebaseAuth mAuth;

    // Default Constructor
    public ForgotPasswordFragment() {
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
       // return inflater.inflate(R.layout.fragment_forgot_password, container, false);

        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        email = view.findViewById(R.id.editTextEmailId);
        buttonsend = view.findViewById(R.id.buttonSend);

        mAuth = FirebaseAuth.getInstance();

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Semail = email.getText().toString().trim();

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

                                } else {
                                    // Log.e(TAG, "Error signing in with email ", task.getException())
//                                    Toast.makeText(ForgotPassword.this, "Already user exists", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(ForgotPassword.this, ResetPassword.class));
                                    mAuth.sendPasswordResetEmail(Semail)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull  Task<Void> task) {
                                                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,new UserLoginFragment()).commit();
                                                }
                                            });
                                }
                            }
                        });

    }
}