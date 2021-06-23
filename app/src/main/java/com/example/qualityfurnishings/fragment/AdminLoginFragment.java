package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminHome;


public class AdminLoginFragment extends Fragment {
    EditText email,password;
    Button login;



    public AdminLoginFragment() {
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
        View view =inflater.inflate(R.layout.fragment_admin_login, container, false);
        email=view.findViewById(R.id.etEmail);
        password=view.findViewById(R.id.etPssword);
        login=view.findViewById(R.id.btAdminLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminHome.class);
                startActivity(intent);
            }
        });
        return view;
    }
}