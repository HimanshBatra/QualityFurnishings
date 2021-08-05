package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.fragment.AdminHomeFragment;
import com.example.qualityfurnishings.fragment.AdminLogout;
import com.example.qualityfurnishings.fragment.AdminProfileFragment;
import com.example.qualityfurnishings.model.Favouties;
import com.example.qualityfurnishings.model.TokenModal;
import com.example.qualityfurnishings.model.UserTestModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class AdminHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedpreferences;
    FirebaseAuth firebaseAuth;
    SharedPreferences.Editor editor;
    String fcm,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getApplicationContext().getSharedPreferences("LANGUAGE",getApplicationContext().MODE_PRIVATE);
        editor = sharedpreferences.edit();
        String lang = sharedpreferences.getString("code","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        //intialize the token
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                fcm=task.getResult();
                Log.d("token", fcm);
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
                            id =listData.getId();
                            Log.d("chh", id);
                            // get the stored token and and set the value of new one
                            DatabaseReference databaseRefer = FirebaseDatabase.getInstance().getReference("FurnitureCategory")
                                    .child("Token").child(id).child("token");
                            databaseRefer.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String status = (String) dataSnapshot.getValue();
                                    Log.d("TAG", "status "+status);
                                    databaseRefer.setValue(fcm);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }

                            });

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        // get the id from the database



//    }
//});
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FurnitureCategory")
//                .child("Token");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                ArrayList<TokenModal> tokenlist;
//                tokenlist = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    TokenModal listData = snapshot.getValue(TokenModal.class);
//                    tokenlist.add(new TokenModal(listData.getToken(),listData.getId()));
//                    id =listData.getId();
//
//                }
//                Log.d("chl", id);
//
////                       fcm = (String) dataSnapshot.getValue();
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });




        getSupportFragmentManager().beginTransaction().replace(R.id.AdminHomeFrame, new AdminHomeFragment()).commit();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.adminbottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;

                switch(item.getItemId())
                {
                    case R.id.adminHome : temp = new AdminHomeFragment();
                    break;
                    case R.id.adminProfile : temp = new AdminProfileFragment();
                    break;
                    case R.id.adminLogout : temp = new AdminLogout();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.AdminHomeFrame,temp).commit();

                return true;
            }
        });
    }
    public void showAdminHomeFragment() {
        bottomNavigationView.setSelectedItemId(R.id.adminHome);
    }
}