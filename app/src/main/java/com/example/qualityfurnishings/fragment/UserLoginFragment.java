package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.MainActivity;
import com.example.qualityfurnishings.activity.UserHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserLoginFragment extends Fragment {
    EditText email,password;
    Button login;
    private FirebaseAuth firebaseAuth;


    public UserLoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        email=view.findViewById(R.id.etEmail);
        password=view.findViewById(R.id.etPssword);
        login=view.findViewById(R.id.btUserLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(this :: signinUser);
        return view;
    }

    private void signinUser(View view) {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getActivity(),UserHome.class);
                    startActivity(intent);
                    Toast.makeText(getContext(),"User Logged In SuccessFully",Toast.LENGTH_SHORT).show();
                    updateUI();

                }
                else {
                    Toast.makeText(getContext(),"Wrong Password or Email",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void updateUI() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user == null){
            Toast.makeText(getContext(),"New User",Toast.LENGTH_SHORT).show();
            return;

        }else{
            Intent intent = new Intent(getActivity(),UserHome.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }
}