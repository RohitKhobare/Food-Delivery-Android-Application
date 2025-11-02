package com.example.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.loginpage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OrderFragment extends Fragment {

Button logout;
EditText email;
EditText name;
EditText phno;
DatabaseReference refphno,refname;
FirebaseAuth firebaseAuth;




    public OrderFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout= view.findViewById(R.id.logout);
        email=view.findViewById(R.id.iemail);
        name=view.findViewById(R.id.iname);
        phno=view.findViewById(R.id.iphno);
        refphno = FirebaseDatabase.getInstance().getReference().child("info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Number");

        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

refphno.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists())
        {
           String data=snapshot.getValue().toString();
           phno.setText(data);
        }


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
        refname = FirebaseDatabase.getInstance().getReference().child("info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Name");


refname.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String data=snapshot.getValue().toString();
        name.setText(data);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences(loginpage.PREFS_NAME,0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.commit();
                startActivity(new Intent(getActivity(), loginpage.class));
            }
        });

    }


}