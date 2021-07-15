package com.example.qualityfurnishings.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.activity.AdminEditProfile;
import com.example.qualityfurnishings.activity.AdminHome;
import com.example.qualityfurnishings.model.AdminModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminProfileFragment extends Fragment {
    TextView name,email,password;
    Button editProfile;
    String stName,stEmail,stPassword;



    public AdminProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        name=(TextView)view.findViewById(R.id.tvName);
        email=(TextView)view.findViewById(R.id.tvEmail);
        password=(TextView)view.findViewById(R.id.tvPassword);
        editProfile=(Button) view.findViewById(R.id.btEditProfile);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("FurnitureCategory").child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    AdminModal admin = snapshot.getValue(AdminModal.class);
                    stName=admin.getName();
                    stEmail=admin.getEmail();
                    stPassword=admin.getPassword();
//                    Log.d("admin", stName);
//                    Log.d("admin1", stEmail);
//                    Log.d("admin2", stPassword);
                    name.setText(stName);
                    email.setText(stEmail);
                    password.setText(stPassword);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminEditProfile.class);
                intent.putExtra("name",stName);
                intent.putExtra("email",stEmail);
                intent.putExtra("password",stPassword);
//                ((AdminHome) getActivity()).startActivity(intent);
                startActivity(intent);

            }
        });

        return view;
    }
}